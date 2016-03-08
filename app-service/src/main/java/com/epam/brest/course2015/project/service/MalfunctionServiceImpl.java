package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.Costs;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.MalfunctionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Transactional
public class MalfunctionServiceImpl implements MalfunctionService {

    private static final Logger LOGGER = LogManager.getLogger();

    private MalfunctionDao malfunctionDao;

    public void setMalfunctionDao(MalfunctionDao malfunctionDao) {
        this.malfunctionDao = malfunctionDao;
    }
    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        LOGGER.info("SERVICE: addMalfunction");
        Assert.hasText(malfunction.getName(), "Name should not be empty!");
        Assert.isNull(malfunction.getMalfunctionId(), "Id should be null!");
        Assert.hasText(malfunction.getDescription(), "Description should not be empty!");
        return malfunctionDao.addMalfunction(malfunction);
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {
        LOGGER.info("SERVICE: delete application by id=" + malfunctionId.toString());
        Assert.notNull(malfunctionId, "Id should not be null!");
        Assert.isTrue(malfunctionId > 0);
        malfunctionDao.deleteMalfunction(malfunctionId);
    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {
        LOGGER.info("SERVICE: update malfunction by id="+malfunction.getMalfunctionId());
        Assert.notNull(malfunction.getMalfunctionId(),"Id should not be null!");
        Assert.notNull(malfunction.getName(), "Name should not be null!");
        Assert.notNull(malfunction.getAuto(), "Auto should not be empty!");
        Assert.notNull(malfunction.getDescription(), "Description should not be empty!");
        malfunctionDao.updateMalfunction(malfunction);
    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        LOGGER.info("SERVICE: get malfunction by id=" + malfunctionId.toString());
        Assert.notNull(malfunctionId, "Id should not be null!");
        return malfunctionDao.getMalfunctionById(malfunctionId);
    }

    @Override
    public List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId) {
        LOGGER.info("SERVICE: get all malfunctions by application id="+applicationId.toString());
        Assert.notNull(applicationId,"Id should not be null");
        Assert.isTrue(applicationId > 0);
        return malfunctionDao.getAllMalfunctionsByIdApplication(applicationId);
    }

    @Override
    public List<Malfunction> getAllMalfunctions() {
        LOGGER.info("SERVICE: get all malfunctions");
        return malfunctionDao.getAllMalfunctions();
    }

    @Override
    public List<Costs> getMalfunctionsCosts() {
        LOGGER.info("SERVICE: get cost for malfunctions");
        return malfunctionDao.getMalfunctionsCosts();
    }

    @Override
    public List<Costs> getApplicationsCosts() {
        LOGGER.info("SERVICE: get costs for applications");
       return malfunctionDao.getApplicationsCosts();
    }

    @Override
    public void addCostsToMalfunction(Integer malfunctionId, Integer costRepair, Integer costService, Integer additionalExpenses) {
        LOGGER.info("SERVICE: add costs for malfunction");
        Assert.notNull(malfunctionId,"Id should not be null");
        Assert.notNull(costRepair,"Cost should not be null");
        Assert.notNull(costService,"Cost should not be null");
        Assert.notNull(additionalExpenses,"Cost should not be null");
        malfunctionDao.addCostsToMalfunction(malfunctionId,costRepair,costService,additionalExpenses);
    }

}
