package com.epam.brest.course2015.project.web;

import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.MalfunctionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MalfunctionRestClient implements MalfunctionDao{

    private static final Logger LOGGER = LogManager.getLogger();
    RestTemplate restTemplate = new RestTemplate();
    private static final String REST_URL="http://localhost:8090/rest/malfunction";

    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        Integer id = restTemplate.postForObject(REST_URL, Malfunction.class, Integer.class);
        LOGGER.info("client request to add malfunction {} and return id {}",malfunction,id);
        return id;
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {
        LOGGER.info("client request delete malfunction by id="+malfunctionId);
        restTemplate.delete(REST_URL+"/delete/{id}",malfunctionId);

    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {
        LOGGER.info("client request update malfunction with id",malfunction.getMalfunctionId());
        restTemplate.put(REST_URL+"/update",malfunction);
    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        LOGGER.info("client request get malfunction by id="+malfunctionId);
        Malfunction malfunction = restTemplate.getForObject(REST_URL+"/{id}",Malfunction.class,malfunctionId);
        return  malfunction;
    }

    @Override
    public void addCostsToMalfunction(Integer malfunctionId, Integer costRepair, Integer costService, Integer additionalExpenses) {
        restTemplate.put(REST_URL+"/{id}/{costRepair}/{costService}/{additionalExpenses}",malfunctionId,costRepair,costService,additionalExpenses);
    }

    @Override
    public List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId) {
        return null;
    }

    @Override
    public List<Malfunction> getAllMalfunctions() {
        return null;
    }

    @Override
    public Integer getCostForMalfunctionById(Integer malfunctionId) {
        return null;
    }

    @Override
    public Integer getCostForMalfunctionsByApplicationId(Integer applicationId) {
        return null;
    }
}
