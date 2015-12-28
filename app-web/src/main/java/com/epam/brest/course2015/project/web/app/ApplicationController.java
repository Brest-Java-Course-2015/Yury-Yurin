package com.epam.brest.course2015.project.web.app;

import com.epam.brest.course2015.project.core.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController  {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.prefix}")
    private String prefix;

    public Integer addApplication(Application application) {
        Integer id = restTemplate.postForObject(prefix+"application",application,Integer.class);
        LOGGER.info("client request for addition application with id=" + id);
        return id;
    }

    public void deleteApplication(Integer applicationId) {
        LOGGER.info("client request for remove application by id="+applicationId);
        restTemplate.delete(prefix+"application/delete/{id}",applicationId);
    }

    public void updateApplication(Integer id, Date updatedDate) {
        String time = String.valueOf(updatedDate.getTime());
        LOGGER.info("date = " + time);
        LOGGER.info("client request for updating application by id=" + id);
        restTemplate.put(prefix+"application/update?id="+id.toString() + "&time="+time,null);
    }

    public Application getApplicationById(Integer applicationId) {
        LOGGER.info("client request for application by id=" + applicationId);
        return restTemplate.getForObject(prefix+"application/{id}",Application.class,applicationId);
    }

    public List<Application> getAllApplications() {
        LOGGER.info("client request for all applications");
        return restTemplate.getForObject(prefix+"applications",List.class);
    }

    public List<Application> getApplicationsBySetDate(Date minDate, Date maxDate) {
        String nDate = String.valueOf(minDate.getTime());
        String xDate = String.valueOf(maxDate.getTime());
        LOGGER.info("client request for all applications by set date");
        return restTemplate.getForObject(prefix+"applications/byDate?minDateTime="+nDate+"&maxDateTime="+ xDate,List.class);
    }
}
