package com.epam.brest.course2015.project.service;


import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.MalfunctionDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mock-spring-test-service.xml"})
public class MockMalfunctionServiceImplTest {

    @Autowired
    private MalfunctionService malfunctionService;

    @Autowired
    private MalfunctionDao mockMalfunctionDao;

    private static final Malfunction malfunction = new Malfunction(null,"name1","auto1","description1",1);

    @After
    public void setUp() {
        verify(mockMalfunctionDao);
    }

    @Test
    public void TestAddMulfunction() {
        expect(mockMalfunctionDao.addMalfunction(malfunction)).andReturn(3);
        replay(mockMalfunctionDao);
        Assert.isTrue(malfunctionService.addMalfunction(malfunction).equals(3));
    }

    @Test
    public void TestDeleteMulfunction() {
        mockMalfunctionDao.deleteMalfunction(2);
        expectLastCall();
        replay(mockMalfunctionDao);
        malfunctionService.deleteMalfunction(2);
    }

}