package com.epam.brest.course2015.project.web;

import com.epam.brest.course2015.project.core.Costs;
import com.epam.brest.course2015.project.core.Malfunction;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:mock-test-rest-client-spring.xml"})
public class MalfunctionRestClientTest {

    @Autowired
    private MalfunctionRestClient malfunctionRestClientDao;

    @Autowired
    private MalfunctionRestClient malfunctionRestClientService;

    private Malfunction malfunction = new Malfunction(null,"name","auto","description",1);


    @After
    public void setUp() {
        verify(malfunctionRestClientDao);
        reset(malfunctionRestClientDao);
    }

    @Test
    public void getAllMalfunctionsTest() {
        List<Malfunction> malfunctionList = new ArrayList<Malfunction>();
        malfunctionList.add(malfunction);
        expect(malfunctionRestClientDao.getAllMalfunctions()).andReturn(malfunctionList);
        replay(malfunctionRestClientDao);
        Integer size = malfunctionRestClientService.getAllMalfunctions().size();
        Assert.isTrue(size.equals(1));
    }

    @Test
    public void addMalfunctionTest() {
        expect(malfunctionRestClientDao.addMalfunction(malfunction)).andReturn(1);
        replay(malfunctionRestClientDao);
        Assert.isTrue(malfunctionRestClientService.addMalfunction(malfunction).equals(1));
    }

    @Test
    public void updateMalfunctionTest() {
        Malfunction newMalfunction = new Malfunction(2, "name3", "auto4", "description5", 1);
        expect(malfunctionRestClientDao.getMalfunctionById(2)).andReturn(newMalfunction);
        malfunctionRestClientDao.updateMalfunction(newMalfunction);
        expectLastCall();
        replay(malfunctionRestClientDao);
        malfunctionRestClientService.updateMalfunction(malfunctionRestClientService.getMalfunctionById(2));
    }

    @Test
    public void deleteMalfunctionTest() {
        malfunctionRestClientDao.deleteMalfunction(1);
        expectLastCall();
        replay(malfunctionRestClientDao);
        malfunctionRestClientService.deleteMalfunction(1);
    }

    @Test
    public void getMalfunctionByIdTest() {
        malfunction.setMalfunctionId(1);
        expect(malfunctionRestClientDao.getMalfunctionById(1)).andReturn(malfunction);
        replay(malfunctionRestClientDao);
        Integer id = malfunctionRestClientService.getMalfunctionById(1).getMalfunctionId();
        Assert.isTrue(id.equals(1));
    }

    @Test
    public void getApplicationCostsTest() {
        Costs costs = new Costs(1,6000);
        List<Costs> costsList = new ArrayList<Costs>();
        costsList.add(costs);
        expect(malfunctionRestClientDao.getApplicationsCosts()).andReturn(costsList);
        replay(malfunctionRestClientDao);
        Assert.isTrue(malfunctionRestClientService.getApplicationsCosts().equals(costsList));
    }

    @Test
    public void getMalfunctionCostsTest() {
        Costs costs = new Costs(1,6000);
        List<Costs> costsList = new ArrayList<Costs>();
        costsList.add(costs);
        expect(malfunctionRestClientDao.getMalfunctionsCosts()).andReturn(costsList);
        replay(malfunctionRestClientDao);
        Assert.isTrue(malfunctionRestClientService.getMalfunctionsCosts().equals(costsList));
    }
}
