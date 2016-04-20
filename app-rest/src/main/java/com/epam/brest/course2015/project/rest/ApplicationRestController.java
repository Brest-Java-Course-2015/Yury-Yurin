package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.Costs;
import com.epam.brest.course2015.project.dao.ApplicationDao;
import com.epam.brest.course2015.project.service.ApplicationService;
import com.epam.brest.course2015.project.service.ApplicationServiceImpl;
import com.epam.brest.course2015.project.service.MalfunctionService;
import com.epam.brest.course2015.project.service.UsersService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import io.swagger.util.Json;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ApplicationRestController extends RouteBuilder {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MalfunctionService malfunctionService;



    /*
    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Integer addApplication(@RequestBody Application application) {
        return applicationService.addApplication(application);
    }

    @RequestMapping(value = "/application/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Application getApplicationById(@PathVariable(value = "id") Integer id) {
        return applicationService.getApplicationById(id);
    }

    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @RequestMapping(value = "/application/delete/{id}" , method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteApplication(@PathVariable(value = "id") Integer id) {
        applicationService.deleteApplication(id);
    }

    @RequestMapping(value =  "/application/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateApplication(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "time") String time) {
        LOGGER.info("date = " + time);
        applicationService.updateApplication(id, getDate(time));
    }

    @RequestMapping(value = "/applications/byDate", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Application> getAllApplicationsByDate(@RequestParam(value = "minDateTime") String minDateTime,
                                                                    @RequestParam(value = "maxDateTime") String maxDateTime) {
        return applicationService.getAllApplicationsByDate(getDate(minDateTime),getDate(maxDateTime));
    }

*/

    @Override
    public void configure() throws Exception {
        restConfiguration().component("jetty").port("8282")
                .contextPath("/")
                .host("0.0.0.0")
                .enableCORS(true);

        rest("/applications")
                .get()
                .route().setHeader("Content-Type", constant("application/json")).to("direct:getAllApplication");

        from("direct:getAllApplication").transacted().process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.setOut(exchange.getIn());
            }
        })
                .log(LoggingLevel.INFO, "before bean")
                .bean(applicationService, "getAllApplications")
                .marshal().
                json(JsonLibrary.Gson, List.class).log(LoggingLevel.INFO, "after json");


        rest("/malfunctions")
                .get()
                .route()
                .setHeader("Content-Type", constant("application/json"))
                .log(LoggingLevel.INFO, "before bean")
                .toD("bean:malfunctionService?method=getAllMalfunctionsByIdApplication(${header.id})")
                .marshal()
                .json(JsonLibrary.Gson, List.class).log(LoggingLevel.INFO, "after json");

        rest("/malfunction")
                .post("/setCosts")
                .route()
                .log(LoggingLevel.INFO, "before bean")
                .toD("bean:malfunctionService?method=addCostsToMalfunction(${header.id}," +
                        "${header.costRepair},${header.costService},${header.additionalExpenses})");


        rest("/malfunction/getCostsMalfunctions")
                .get()
                .route()
                .setHeader("Content-Type", constant("application/json"))
                .bean(malfunctionService, "getMalfunctionsCosts")
                .marshal()
                .json(JsonLibrary.Gson, List.class);

        rest("/malfunction/getCostsApplications")
                .get()
                .route()
                .setHeader("Content-Type", constant("application/json"))
                .bean(malfunctionService, "getApplicationsCosts")
                .marshal()
                .json(JsonLibrary.Gson, List.class);


        rest("/malfunction/delete")
                .get().route()
                .toD("bean:malfunctionService?method=deleteMalfunction(${header.id})");

        rest("application")
                .post("update").route()
                .toD("bean:applicationService?method=updateApplication(${header.id}," +
                        "${header.time})");

        rest("/application/")
                .get("/delete/{id}").route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setOut(exchange.getIn());
                    }
                })
                .toD("bean:applicationService?method=deleteApplication(${header.id})");

        rest("/app/new").description("User rest service")
                .consumes("application/json").produces("application/json")
                .post().description("Updates or create a user").type(Application.class)
                .to("bean:applicationService?method=addApplication");

        rest("application/{id}")
                .get().route()
                .setHeader("Content-Type", constant("application/json"))
                .toD("bean:applicationService?method=getApplicationById(${header.id})")
                .marshal()
                .json(JsonLibrary.Gson, Application.class);

        rest("applications/byDate")
                .get()
                .outType(List.class)
                .route()
                .setHeader("Content-Type", constant("application/json"))
                .toD("bean:applicationService?method=getAllApplicationsByDate(${header.minDateTime}," +
                        "${header.maxDateTime})")
                .marshal()
                .json(JsonLibrary.Gson, List.class);

        rest("/user/add")
                .get()
                .outType(Integer.TYPE)
                .toD("bean:usersService?method=addUser(${header.login},${header.hash})");

        rest("/user/check")
                .get().outType(Boolean.TYPE).route()
                .toD("bean:usersService?method=checkUser(${header.login},${header.hash})");

        rest("/users")
                .get()
                .route()
                .setHeader("Content-Type", constant("application/json"))
                .log(LoggingLevel.INFO, "before bean")
                .bean(usersService, "getAllUsers")
                .marshal()
                .json(JsonLibrary.Gson, List.class).log(LoggingLevel.INFO, "after json");

        rest("/user")
                .get("/getR")
                .outType(Integer.TYPE)
                .route()
                .toD("bean:usersService?method=getR(${header.login})");

        rest("/user")
                .get("/getGenerateR")
                .outType(Integer.TYPE)
                .route()
                .bean(usersService, "generateR");

        rest("/user")
                .get("/getN")
                .outType(Integer.TYPE)
                .route()
                .toD("bean:usersService?method=getN(${header.login})");

        rest("/user")
                .get("/getNewN")
                .outType(Integer.TYPE)
                .route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.setOut(exchange.getIn());
                    }
                })
                .bean(usersService, "getNewN");


    }
}
