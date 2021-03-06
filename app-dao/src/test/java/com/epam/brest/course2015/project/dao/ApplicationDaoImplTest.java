package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.peer.ListPeer;
import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-dao.xml"})
@Transactional
@Sql(scripts = "/alter-application-script.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ApplicationDaoImplTest {

    @Autowired
    private ApplicationDao applicationDao;

    private Application application = new Application(null,new Date(),new Date());



    @Test
    public void TestGetAllApplications() {
        int size = applicationDao.getAllApplications().size();
        Assert.assertTrue(size == 2);
    }

    @Test
    public void TestDeleteApplicationById() {
        int size = applicationDao.getAllApplications().size();
        applicationDao.deleteApplication(2);
        Assert.assertNotEquals(size, applicationDao.getAllApplications().size());
    }

    @Test
    public void TestGetApplicationsByDate() {
        Date minDate = new Date();
        Date maxDate = new Date();
        applicationDao.addApplication(new Application(null,minDate,maxDate));
        minDate.setMonth(minDate.getMonth()-1);
        maxDate.setMonth(maxDate.getMonth()+1);
        List<Application> applicationList = applicationDao.getApplicationsBySetDate(minDate,maxDate);
        Assert.assertTrue(applicationList.size()==1);

    }
    @Test
    public void TestGetApplicationById() {
        List<Application> list = applicationDao.getAllApplications();
        Integer index = applicationDao.addApplication(application);
        Application newApplication = applicationDao.getApplicationById(index);
        Assert.assertEquals(index, newApplication.getApplicationId());
    }

    @Test
    public void TestUpdateApplicationById() {
        applicationDao.updateApplication(1,new Date());
        Application newApplication = applicationDao.getApplicationById(1);
        Assert.assertTrue(newApplication.getCreatedDate().equals(newApplication.getCreatedDate()));
        Assert.assertTrue(newApplication.getUpdatedDate().equals(newApplication.getUpdatedDate()));
    }

    @Test
    public void TestAddApplication() {
        int size = applicationDao.getAllApplications().size();
        int index = applicationDao.addApplication(application);
        Assert.assertNotEquals(size, applicationDao.getAllApplications().size());
    }
}
