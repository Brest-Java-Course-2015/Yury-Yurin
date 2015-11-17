package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.dao.ApplicationDao;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by blondeks on 11/17/15.
 */
public class ApplicationServiceImpl implements ApplicationService {

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
        Assert.isNull(applicationId,"Id should not be null!");
        applicationDao.deleteApplication(applicationId);
    }

    @Override
    public void updateApplication(Application application) {
        Assert.notNull(application.getApplicationId(),"Id should not be null");
        applicationDao.updateApplication(application);
    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        Assert.isNull(applicationId,"Id should not be null!");
        return applicationDao.getApplicationById(applicationId);
    }

    @Override
    public List<Application> getAllApplications() {
        List<Application> applicationList = applicationDao.getAllApplications();
        Assert.notEmpty(applicationList,"Empty list of applications");
        return applicationList;
    }
}
