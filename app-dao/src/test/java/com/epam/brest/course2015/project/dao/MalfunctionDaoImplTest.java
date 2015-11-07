package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Malfunction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-dao.xml"})
public class MalfunctionDaoImplTest {

    private Malfunction malfunction = new Malfunction(null,"malfunction1","auto1","description1");

    @Autowired
    private MalfunctionDao mockMalfunctionDao;

    @Test
    public void TestAddMalfunction() {
        expect(mockMalfunctionDao.addMalfunction(malfunction)).andReturn(3);
        replay(mockMalfunctionDao);
        int id = mockMalfunctionDao.addMalfunction(malfunction);
        Assert.assertTrue(id == 3);
    }
}
