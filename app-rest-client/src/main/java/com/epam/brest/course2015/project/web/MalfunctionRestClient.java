package com.epam.brest.course2015.project.web;

import com.epam.brest.course2015.project.core.ApplicationCosts;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.MalfunctionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MalfunctionRestClient implements MalfunctionDao{

    private static final Logger LOGGER = LogManager.getLogger();
    RestTemplate restTemplate = new RestTemplate();

    @Value("${url.prefix}")
    private String prefix;


    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        Integer id = restTemplate.postForObject(prefix + "malfunction",malfunction, Integer.class);
        LOGGER.info("client request to add malfunction {} and return id {}", malfunction, id);
        return id;
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {
        LOGGER.info("client request delete malfunction by id="+malfunctionId);
        restTemplate.delete(prefix+"malfunction/delete/{id}",malfunctionId);

    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {
        LOGGER.info("client request update malfunction with id",malfunction.getMalfunctionId());
        restTemplate.put(prefix+"malfunction/update",malfunction);
    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        LOGGER.info("client request get malfunction by id="+malfunctionId);
        Malfunction malfunction = restTemplate.getForObject(prefix+"malfunction/{id}",Malfunction.class,malfunctionId);
        return  malfunction;
    }

    @Override
    public void addCostsToMalfunction(Integer malfunctionId, Integer costRepair, Integer costService, Integer additionalExpenses) {
        LOGGER.info("client request for add costs: {} , {} , {}, by id={}",costRepair,costService,additionalExpenses,malfunctionId);
        restTemplate.put(prefix + "malfunction/setCosts?id=" + malfunctionId.toString() +
                "&costRepair=" + costRepair.toString() +
                "&costService=" + costService.toString() +
                "&additionalExpenses=" + additionalExpenses.toString(), null);
    }

    @Override
    public List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId)
    {
        LOGGER.info("client request for all malfunction by application id=" + applicationId);
        return restTemplate.getForObject(prefix+"malfunctions/{id}",List.class,applicationId);
    }

    @Override
    public List<Malfunction> getAllMalfunctions() {
        LOGGER.info("client request for all malfunctions");
        return restTemplate.getForObject(prefix+"malfunctions",List.class);
    }

    @Override
    public List<ApplicationCosts> getMalfunctionsCosts() {
        return restTemplate.getForObject(prefix+"malfunction/getCostsMalfunctions",List.class);
    }

    @Override
    public List<ApplicationCosts> getApplicationsCosts() {
        return  restTemplate.getForObject(prefix+"malfunction/getCostsApplications",List.class);
    }

}
