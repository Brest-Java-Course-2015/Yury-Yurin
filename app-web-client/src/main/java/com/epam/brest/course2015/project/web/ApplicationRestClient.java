package com.epam.brest.course2015.project.web;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.ApplicationDao;
import com.epam.brest.course2015.project.dao.MalfunctionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

public class ApplicationRestClient implements ApplicationDao {

    private static final Logger LOGGER = LogManager.getLogger();
    RestTemplate restTemplate = new RestTemplate();
    private static final String REST_URL="http://localhost:8080/rest/application";

    @Override
    public Integer addApplication(Application application) {
        Integer id = restTemplate.postForObject(REST_URL,Application.class,Integer.class);
        LOGGER.info("client request for addition application with id=" + id);
        return id;
    }

    @Override
    public void deleteApplication(Integer applicationId) {
        LOGGER.info("client request for remove application by id="+applicationId);
        restTemplate.delete(REST_URL+"/delete/{id}",applicationId);
    }

    @Override
    public void updateApplication(Integer applicationId, Date updatedDate) {
        LOGGER.info("client request for updating application by id=",applicationId);
        restTemplate.put(REST_URL+"/update/{id}",applicationId);
    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        LOGGER.info("client request for application by id=" + applicationId);
        return restTemplate.getForObject(REST_URL+"{id}",Application.class,applicationId);
    }

    @Override
    public List<Application> getAllApplications() {
        LOGGER.info("client request for all applications");
        return restTemplate.getForObject(REST_URL+"s",List.class);
    }

    @Override
    public List<Application> getApplicationsBySetDate(Date minDate, Date maxDate) {
        LOGGER.info("client request for all applications by set date");
        return restTemplate.getForObject(REST_URL+"s/byDate/{minDateTime}/{maxDateTime}",List.class,minDate,maxDate);
    }
}