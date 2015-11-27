package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.dao.ApplicationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger LOGGER = LogManager.getLogger();

    private ApplicationDao applicationDao;

    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }
    @Override
    public Integer addApplication(Application application) {
        Assert.isNull(application.getApplicationId(), "Id should be null!");
        return applicationDao.addApplication(application);
    }

    @Override
    public void deleteApplication(Integer applicationId) {
        Assert.notNull(applicationId,"Id should not be null!");
        applicationDao.deleteApplication(applicationId);
    }

    @Override
    public void updateApplication(Integer applicationId, Date updatedDate) {
        Assert.notNull(applicationId,"Id should not be null");
        applicationDao.updateApplication(applicationId,updatedDate);
    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        Assert.notNull(applicationId,"Id should not be null!");
        return applicationDao.getApplicationById(applicationId);
    }

    @Override
    public List<Application> getAllApplications() {
        List<Application> applicationList = applicationDao.getAllApplications();
        return applicationList;
    }
}
