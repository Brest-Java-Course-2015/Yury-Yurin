package com.epam.brest.course2015.project.web.app;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.ApplicationCosts;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.service.ApplicationService;
import com.epam.brest.course2015.project.service.MalfunctionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class ApplicationController  {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private MalfunctionService malfunctionService;

    @RequestMapping("/applicationsByDate")
    public ModelAndView getAppById(@RequestParam("dateMin") String dateMin,
                                   @RequestParam("dateMax") String dateMax) {
        List<Application> applicationList =applicationService.getAllApplicationsByDate(getDate(dateMin), getDate(dateMax));
        ModelAndView modelAndView = new ModelAndView("userTableByDate","applications",applicationList);
       /* if(applicationList.size()!=0) {
            List<Malfunction> malfunctionList = new ArrayList<Malfunction>();
            for (Application application:applicationList) {
                List<Malfunction> malfunctionList1 = malfunctionService.getAllMalfunctionsByIdApplication(application.getApplicationId());
                malfunctionList.addAll(malfunctionList1);
            }
            modelAndView.addObject("malfunctions", malfunctionList);
        }*/
        List<Malfunction> malfunctionList = malfunctionService.getAllMalfunctions();
        modelAndView.addObject("malfunctions", malfunctionList);
        List<ApplicationCosts> applicationCostsList = malfunctionService.getApplicationsCosts();
        List<ApplicationCosts> malfunctionCostsList = malfunctionService.getMalfunctionsCosts();
        modelAndView.addObject("malfunctionsCosts",malfunctionCostsList);
        modelAndView.addObject("applicationsCosts", applicationCostsList);
        LOGGER.debug("by date view");
        return modelAndView;
    }

    @RequestMapping("/applications")
    public ModelAndView getMainDataForView() {
        List<Application> applicationList = applicationService.getAllApplications();
        List<Malfunction> malfunctionList = malfunctionService.getAllMalfunctions();
        List<ApplicationCosts> applicationCostsList = malfunctionService.getApplicationsCosts();
        List<ApplicationCosts> malfunctionCostsList = malfunctionService.getMalfunctionsCosts();
        LOGGER.debug("main view");
        ModelAndView modelAndView = new ModelAndView("indexForUserAll","applications",applicationList);
        modelAndView.addObject("malfunctions",malfunctionList);
        modelAndView.addObject("malfunctionsCosts",malfunctionCostsList);
        modelAndView.addObject("applicationsCosts", applicationCostsList);
        return modelAndView;
    }

    @RequestMapping("/applicationSubmit")
    public String addApplicationSubmit(@RequestBody Malfunction malfunction) {
        if(malfunction.getApplicationId()==null) {
            Application application = new Application(null, new Date(), new Date());
            Integer id = applicationService.addApplication(application);
            malfunction.setApplicationId(id);
        }
        malfunctionService.addMalfunction(malfunction);
        applicationService.updateApplication(malfunction.getApplicationId(), new Date());
       return "forward:/applications";
    }

    @RequestMapping("/application")
    public ModelAndView addApplication(@RequestParam("id") Integer id) {
        return new ModelAndView("application","id",id);
    }

    @RequestMapping("/deleteApplication")
    public String deleteApplication(@RequestParam("id") Integer id) {
        applicationService.deleteApplication(id);
        return "redirect:/applications";
    }

    @RequestMapping("/deleteMalfunction")
    public String deleteMalfunction(@RequestParam("malId") Integer malfunctionId,
                                    @RequestParam("appId") Integer applicationId) {
        malfunctionService.deleteMalfunction(malfunctionId);
        applicationService.updateApplication(applicationId, new Date());
        return "redirect:/applications";
    }

    @RequestMapping("/updateMalfunctionSubmit")
    public String updateMalfunctionSubmit(@RequestBody Malfunction malfunction) {
        malfunctionService.updateMalfunction(malfunction);
        applicationService.updateApplication(malfunction.getApplicationId(),new Date());
        return "forward:/applications";
    }

    @RequestMapping(value = "/updateMalfunction")
    public ModelAndView updateMalfunction(@RequestParam("id") Integer id) {
        Malfunction malfunction = malfunctionService.getMalfunctionById(id);
        return new ModelAndView("update","malfunction",malfunction);
    }


    private static Date getDate(String date) {
        Date newDate = new Date();
        newDate.setTime(Long.valueOf(date));
        LOGGER.info(newDate.toString());
        return newDate;
    }

}
