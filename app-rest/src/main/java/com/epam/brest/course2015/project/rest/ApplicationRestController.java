package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.dao.ApplicationDao;
import com.epam.brest.course2015.project.service.ApplicationService;
import com.epam.brest.course2015.project.service.ApplicationServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
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


public class ApplicationRestController extends RouteBuilder {

    @Autowired
    private ApplicationService applicationService;

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

     private static Date getDate(String date) {
             Date newDate = new Date();
             newDate.setTime(Long.valueOf(date));
             LOGGER.info(newDate.toString());
             return newDate;
     }
 */
    @Override
    public void configure() throws Exception {
        rest("/applications").
                get().outType(List.class).route().bean(applicationService,"getAllApplications");
      }
}
