package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.MalfunctionDao;
import org.springframework.util.Assert;

import java.util.List;

public class MalfunctionServiceImpl implements MalfunctionService {

    private MalfunctionDao malfunctionDao;

    public void setMalfunctionDao(MalfunctionDao malfunctionDao) {
        this.malfunctionDao = malfunctionDao;
    }
    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        Assert.hasText(malfunction.getName(), "Name should not be null!");
        Assert.isNull(malfunction.getMalfunctionId(), "Id should be null!");
        Assert.hasText(malfunction.getDescription(),"Description should not be empty!");
        return malfunctionDao.addMalfunction(malfunction);
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {
        Assert.notNull(malfunctionId, "Id should not be null!");
        Assert.isTrue(malfunctionId > 0);
        malfunctionDao.deleteMalfunction(malfunctionId);
    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {
        Assert.notNull(malfunction.getMalfunctionId(),"Id should not be null!");
        Assert.hasText(malfunction.getName(), "Name should not be null!");
        Assert.hasText(malfunction.getAuto(), "Auto should not be empty!");
        Assert.hasText(malfunction.getDescription(), "Description should not be empty!");
        malfunctionDao.updateMalfunction(malfunction);
    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        Assert.notNull(malfunctionId,"Id should not be null!");
        return malfunctionDao.getMalfunctionById(malfunctionId);
    }

    @Override
    public List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId) {
        Assert.notNull(applicationId,"Id should not be null");
        Assert.isTrue(applicationId>0);
        return malfunctionDao.getAllMalfunctionsByIdApplication(applicationId);
    }

    @Override
    public void addCostsToMalfunction(Integer malfunctionId, Integer costRepair, Integer costService, Integer additionalExpenses) {
        Assert.notNull(malfunctionId,"Id should not be null");
        Assert.notNull(costRepair,"Cost should not be null");
        Assert.notNull(costService,"Cost should not be null");
        Assert.notNull(additionalExpenses,"Cost should not be null");
        malfunctionDao.addCostsToMalfunction(malfunctionId,costRepair,costService,additionalExpenses);
    }

}
