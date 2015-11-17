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

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mock-spring-test-service.xml"})
public class MockMalfunctionServiceImplTest {

    @Autowired
    private MalfunctionService malfunctionService;

    @Autowired
    private MalfunctionDao mockMalfunctionDao;
    private List<Malfunction> malfunctions  = new ArrayList<Malfunction>();
    private static final Malfunction malfunction = new Malfunction(null,"name1","auto1","description1",1);

    @After
    public void setUp() {
        verify(mockMalfunctionDao);
        reset(mockMalfunctionDao);
    }

    @Test
    public void TestAddMulfunction() {
        expect(mockMalfunctionDao.addMalfunction(malfunction)).andReturn(3);
        replay(mockMalfunctionDao);
        Assert.isTrue(malfunctionService.addMalfunction(malfunction).equals(3));
    }

    @Test
    public void TestDeleteMalfunction() {
        mockMalfunctionDao.deleteMalfunction(2);
        expectLastCall();
        replay(mockMalfunctionDao);
        malfunctionService.deleteMalfunction(2);
    }

    @Test
    public void TestGetAllMalfunctionsByIdApplication() {
        malfunctions.add(malfunction);
        mockMalfunctionDao.getAllMalfunctionsByIdApplication(1);
        expectLastCall().andReturn(malfunctions);
        replay(mockMalfunctionDao);
         malfunctionService.getAllMalfunctionsByIdApplication(1);
    }

    @Test
    public void TestUpdateMalfunction() {
        Malfunction newMalfunction = new Malfunction(2,"name3","auto4","description5",1);
        expect(mockMalfunctionDao.getMalfunctionById(newMalfunction.getMalfunctionId())).andReturn(newMalfunction);
        mockMalfunctionDao.updateMalfunction(newMalfunction);
        expectLastCall();
        replay(mockMalfunctionDao);
        malfunctionService.updateMalfunction(malfunctionService.getMalfunctionById(2));
    }

    @Test
    public void TestGetMalfunctionById() {
        Malfunction newMalfunction = new Malfunction(2,"name3","auto4","description5",1);
        expect(mockMalfunctionDao.getMalfunctionById(2)).andReturn(newMalfunction);
        replay(mockMalfunctionDao);
        Assert.isTrue(malfunctionService.getMalfunctionById(2).equals(newMalfunction));
    }
}