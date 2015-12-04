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
    private static final String REST_URL="http://localhost:8080/rest/";

    @Override
    public Integer addApplication(Application application) {
        return null;
    }

    @Override
    public void deleteApplication(Integer applicationId) {

    }

    @Override
    public void updateApplication(Integer applicationId, Date updatedDate) {

    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        return null;
    }

    @Override
    public List<Application> getAllApplications() {
        return null;
    }

    @Override
    public List<Application> getApplicationsBySetDate(Date minDate, Date maxDate) {
        return null;
    }
}
