package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.ApplicationCosts;
import com.epam.brest.course2015.project.core.Malfunction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-dao.xml"})
@Transactional
@Sql(scripts = "/alter-malfunction-script.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class MalfunctionDaoImplTest {

    private Malfunction malfunction = new Malfunction(null,"malfunction1","auto1","description1",1);

    @Autowired
    private MalfunctionDao malfunctionDao;

    @Test
    public void TestGetAllMalfunctionsByApplicationId() {
        List<Malfunction> malfunctions = malfunctionDao.getAllMalfunctionsByIdApplication(1);
        Assert.assertTrue(malfunctions.size() == 2);
    }

    @Test
    public void TestAddMalfunction() {
        int size = malfunctionDao.getAllMalfunctionsByIdApplication(1).size();
        malfunctionDao.addMalfunction(malfunction);
        Assert.assertNotEquals(size, malfunctionDao.getAllMalfunctionsByIdApplication(1).size());
    }

    @Test
    public void TestGetMalfunctionById() {
        int index = malfunctionDao.addMalfunction(malfunction);
        Malfunction newMalfunction = malfunctionDao.getMalfunctionById(3);
        Assert.assertTrue(newMalfunction.getMalfunctionId().equals(index));
    }



    @Test
    public void TestDeleteMalfunction() {
        int size = malfunctionDao.getAllMalfunctionsByIdApplication(1).size();
        malfunctionDao.deleteMalfunction(1);
        Assert.assertNotEquals(size, malfunctionDao.getAllMalfunctionsByIdApplication(1).size());
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

    @Test
    public void TestAddCostsToMalfunction() {
        malfunctionDao.addCostsToMalfunction(2, 1000, 2000, 3000);
        Malfunction malfunction = malfunctionDao.getMalfunctionById(2);
        Assert.assertNotNull(malfunction.getCostRepair());
        Assert.assertNotNull(malfunction.getCostService());
        Assert.assertNotNull(malfunction.getAdditionalExpenses());
    }

    @Test
    public void TestGetAllMalfunctions() {
        int size = malfunctionDao.getAllMalfunctions().size();
        Assert.assertTrue(size == 2);
    }

    @Test
    public void TestGetCostById() {
        malfunctionDao.addCostsToMalfunction(1, 1000, 2000, 3000);
        malfunctionDao.addCostsToMalfunction(2, 1000, 2000, 3000);
        List<ApplicationCosts> costs = malfunctionDao.getMalfunctionsCosts();
        Assert.assertTrue(costs.get(0).getCost() == 6000);
    }

    @Test
    public void TestGetCostByApplicationId() {
        malfunctionDao.addCostsToMalfunction(2, 1000, 2000, 3000);
        malfunctionDao.addCostsToMalfunction(1, 1000, 2000, 3000);
        List<ApplicationCosts> costs = malfunctionDao.getApplicationsCosts();
        Assert.assertTrue(costs.get(0).getCost() == 12000);
    }
}
