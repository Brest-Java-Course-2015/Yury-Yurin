package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.dao.ApplicationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger LOGGER = LogManager.getLogger();

    private ApplicationDao applicationDao;

    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }
    @Override
    public Integer addApplication(Application application) {
        LOGGER.info("SERVICE: addApplication");
        Assert.isNull(application.getApplicationId(), "Id should be null!");
        return applicationDao.addApplication(application);
    }

    @Override
    public void deleteApplication(Integer applicationId) {
        LOGGER.info("SERVICE: delete application by id="+applicationId.toString());
        Assert.notNull(applicationId,"Id should not be null!");
        applicationDao.deleteApplication(applicationId);
    }

    @Override
    public void updateApplication(Integer applicationId, String updatedDate) {
        LOGGER.info("SERVICE: updateApplication by id="+applicationId.toString());
        Assert.notNull(applicationId,"Id should not be null");
        applicationDao.updateApplication(applicationId,getDate(updatedDate));
    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        LOGGER.info("SERVICE: get application by id="+applicationId.toString());
        Assert.notNull(applicationId,"Id should not be null!");
        return applicationDao.getApplicationById(applicationId);
    }

    @Override
    public List<Application> getAllApplications() {
        LOGGER.info("getAllApplications");
        return applicationDao.getAllApplications();
    }

    @Override
    public List<Application> getAllApplicationsByDate(String minDate, String maxDate) {
        LOGGER.info("getAllApplicationsBydate from: " + minDate + " To: " + maxDate);
        return applicationDao.getApplicationsBySetDate(getDate(minDate),getDate(maxDate));
    }

    private static Date getDate(String date) {
        Date newDate = new Date();
        newDate.setTime(Long.valueOf(date));
        LOGGER.info(newDate.toString());
        return newDate;
    }
}
