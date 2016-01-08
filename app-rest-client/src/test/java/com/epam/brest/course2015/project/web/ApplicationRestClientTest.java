package com.epam.brest.course2015.project.web;

import com.epam.brest.course2015.project.core.Application;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mock-test-rest-client-spring.xml"})
public class ApplicationRestClientTest {

    @Autowired
    private ApplicationRestClient applicationRestClientDao;

    @Autowired
    private ApplicationRestClient applicationRestClientService;

    private Application application = new Application(null, new Date(), new Date());


    @After
    public void setUp() {
        verify(applicationRestClientDao);
        reset(applicationRestClientDao);
    }

    @Test
    public void getAllApplicationTest() {
        List<Application> applicationList = new ArrayList<Application>();
        application.setApplicationId(1);
        applicationList.add(application);
        expect(applicationRestClientDao.getAllApplications()).andReturn(applicationList);
        replay(applicationRestClientDao);
        Integer size = applicationRestClientService.getAllApplications().size();
        Assert.isTrue(size.equals(1));
    }

    @Test
    public void addApplicationTest() {
        expect(applicationRestClientDao.addApplication(application)).andReturn(1);
        replay(applicationRestClientDao);
        Integer id = applicationRestClientService.addApplication(application);
        Assert.isTrue(id.equals(1));
    }


    @Test
    public void deleteApplicationTest() {
        applicationRestClientDao.deleteApplication(1);
        expectLastCall();
        replay(applicationRestClientDao);
        applicationRestClientService.deleteApplication(1);
    }


    @Test
    public void getApplicationByIdTest() {
        application.setApplicationId(1);
        expect(applicationRestClientDao.getApplicationById(1)).andReturn(application);
        replay(applicationRestClientDao);
        Assert.isTrue(applicationRestClientService.getApplicationById(1).equals(application));
    }

    @Test
    public void getApplicationByDateTest() {
        Date minDate = new Date();
        Date maxDate = new Date();
        minDate.setMonth(minDate.getMonth()-1);
        maxDate.setMonth(maxDate.getMonth() + 1);
        List<Application> applicationList = new ArrayList<Application>();
        application.setApplicationId(1);
        applicationList.add(application);
        expect(applicationRestClientDao.getApplicationsBySetDate(minDate,maxDate)).andReturn(applicationList);
        replay(applicationRestClientDao);
        Integer size = applicationRestClientService.getApplicationsBySetDate(minDate,maxDate).size();
        Assert.isTrue(size.equals(1));
    }


}
