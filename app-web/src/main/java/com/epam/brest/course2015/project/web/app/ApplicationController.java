package com.epam.brest.course2015.project.web.app;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.service.ApplicationService;
import com.epam.brest.course2015.project.service.MalfunctionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController  {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private MalfunctionService malfunctionService;

    @RequestMapping("/applications")
    public ModelAndView launchApplicationForm() {
        List<Application> applicationList = applicationService.getAllApplications();
        List<Malfunction> malfunctionList = malfunctionService.getAllMalfunctions();
        LOGGER.debug("get all applications");
        ModelAndView modelAndView = new ModelAndView("index","applications",applicationList);
        modelAndView.addObject("malfunctions",malfunctionList);
        return modelAndView;
    }


}