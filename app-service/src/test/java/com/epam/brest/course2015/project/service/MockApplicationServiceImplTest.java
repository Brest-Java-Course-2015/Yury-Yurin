package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.dao.ApplicationDao;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mock-spring-test-service.xml"})
public class MockApplicationServiceImplTest {

    @Autowired
    private ApplicationDao mockApplicationDao;

    @Autowired
    private ApplicationService applicationService;

    final static private Application application = new Application(null,new Date(),new Date());

    @After
    public void setUp() {
        verify(mockApplicationDao);
        reset(mockApplicationDao);
    }

    @Test
    public void TestAddApplication() {
        expect(mockApplicationDao.addApplication(application)).andReturn(1);
        replay(mockApplicationDao);
        Assert.isTrue(applicationService.addApplication(application).equals(1));
    }

    @Test
    public void TestDeleteApplication() {
        mockApplicationDao.deleteApplication(2);
        expectLastCall();
        replay(mockApplicationDao);
        applicationService.deleteApplication(2);
    }

    @Test
    public void TestGetApplicationById() {
        application.setApplicationId(1);
        expect(mockApplicationDao.getApplicationById(1)).andReturn(application);
        replay(mockApplicationDao);
        Assert.isTrue(applicationService.getApplicationById(1).equals(application));
    }

    @Test
    public void TestUpdateApplication() {
        application.setUpdatedDate(new Date());
        application.setApplicationId(3);
        mockApplicationDao.updateApplication(3,application.getUpdatedDate());
        expectLastCall();
        replay(mockApplicationDao);
        applicationService.updateApplication(3,application.getUpdatedDate().toString());
    }

    @Test
    public void TestGetAllApplications() {
        List<Application> applicationList = new ArrayList<Application>();
        applicationList.add(application);
        expect(mockApplicationDao.getAllApplications()).andReturn(applicationList);
        replay(mockApplicationDao);
        Assert.isTrue(applicationList.size() == applicationService.getAllApplications().size());
    }

}
