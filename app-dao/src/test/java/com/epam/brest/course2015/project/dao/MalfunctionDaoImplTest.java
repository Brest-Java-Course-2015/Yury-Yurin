package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Malfunction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-dao.xml"})
@Transactional
public class MalfunctionDaoImplTest {

    private Malfunction malfunction = new Malfunction(null,"malfunction1","auto1","description1");

    @Autowired
    private MalfunctionDao malfunctionDao;

    @Test
    public void TestGetAllMalfunctions() {
        List<Integer> malfunctions = malfunctionDao.getAllMalfunctionsByIdApplication(1);
        Assert.assertTrue(malfunctions.size() == 2);
    }

    @Test
    public void TestGetMalfunctionById() {
        malfunction.setMalfunctionId(3);
        malfunctionDao.addMalfunction(malfunction);
        Malfunction newMalfunction = malfunctionDao.getMalfunctionById(3);
        Assert.assertTrue(malfunction.getMalfunctionId().equals(newMalfunction.getMalfunctionId()));
    }

    @Test
    public void TestAddMalfunction() {
        int size = malfunctionDao.getAllMalfunctionsByIdApplication(1).size();
        malfunctionDao.addMalfunction(malfunction);
        Assert.assertNotEquals(size,malfunctionDao.getAllMalfunctionsByIdApplication(1).size());
    }

    @Test
    public void TestDeleteMalfunction() {
        int size = malfunctionDao.getAllMalfunctionsByIdApplication(1).size();
        malfunctionDao.deleteMalfunction(1);
        Assert.assertNotEquals(size,malfunctionDao.getAllMalfunctionsByIdApplication(1).size());
    }

    @Test
    public void TestUpdateMalfunction() {
        Malfunction newMalfunction1 = malfunctionDao.getMalfunctionById(1);
        newMalfunction1.setAuto("Subaru");
        malfunctionDao.updateMalfunction(newMalfunction1);
        Malfunction newMalfunction2 = malfunctionDao.getMalfunctionById(newMalfunction1.getMalfunctionId());
        Assert.assertTrue(newMalfunction1.getName().equals(newMalfunction2.getName()));
        Assert.assertTrue(newMalfunction1.getAuto().equals(newMalfunction2.getAuto()));
        Assert.assertTrue(newMalfunction1.getDescription().equals(newMalfunction2.getDescription()));
    }
}
