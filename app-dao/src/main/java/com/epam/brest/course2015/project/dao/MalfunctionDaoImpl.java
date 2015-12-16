package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Malfunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import static com.epam.brest.course2015.project.core.Malfunction.MalfunctionFields.*;

public class MalfunctionDaoImpl implements MalfunctionDao {
    private RowMapper<Malfunction> malfunctionMapper = new BeanPropertyRowMapper<Malfunction>(Malfunction.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${malfunction.insertMalfunction}")
    private String addMalfunction;

    @Value("${malfunction.getMalfunctions}")
    private String getMalfunctions;

    @Value("${malfunction.deleteMalfunctionById}")
    private String deleteMalfunctionById;

    @Value("${malfunction.getMalfunctionById}")
    private String getMalfunction;

    @Value("${malfunction.updateMalfunctionById}")
    private String updateMalfunctionById;

    @Value("${malfunction.addCosts}")
    private String addCosts;

    @Value("${malfunction.getAllMalfunctions}")
    private String getAllMalfunctions;

    @Value("${malfunction.getCostById}")
    private String getCostById;

    public MalfunctionDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public void addCostsToMalfunction(Integer malfunctionId, Integer costRepair, Integer costService, Integer additionalExpenses) {
        LOGGER.info("DAO:Add all costs for malfunction with id=" + malfunctionId.toString());
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put(MALFUNCTION_ID.getValue(),malfunctionId);
        hashMap.put(COST_REPAIR.getValue(),costRepair);
        hashMap.put(COST_SERVICE.getValue(),costService);
        hashMap.put(ADDITIONAL_EXPENSES.getValue(), additionalExpenses);
        namedParameterJdbcTemplate.update(addCosts, hashMap);
    }
    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        LOGGER.info("DAO:Add new malfunction");
        KeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addMalfunction,getParametersMap(malfunction),key);
        return key.getKey().intValue();
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {
        LOGGER.info("DAO:Delete malfunction by id="+malfunctionId.toString());
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put(MALFUNCTION_ID.getValue(),malfunctionId);
        namedParameterJdbcTemplate.update(deleteMalfunctionById, hashMap);
    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        LOGGER.info("DAO:Get malfunction by id=" + malfunctionId.toString());
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put(MALFUNCTION_ID.getValue(), malfunctionId);
        return namedParameterJdbcTemplate.queryForObject(getMalfunction, hashMap, malfunctionMapper);
    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {
        LOGGER.info("DAO:Update malfunction by id="+malfunction.getMalfunctionId().toString());
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put(MALFUNCTION_ID.getValue(),malfunction.getMalfunctionId());
        hashMap.put(NAME.getValue(),malfunction.getName());
        hashMap.put(AUTO.getValue(),malfunction.getAuto());
        hashMap.put(DESCRIPTION.getValue(), malfunction.getDescription());
        namedParameterJdbcTemplate.update(updateMalfunctionById, hashMap);
    }

    @Override
    public List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId) {
        LOGGER.info("DAO:Get list of malfunction by application id="+applicationId.toString());
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(APPLICATION_ID.getValue(), applicationId);
        return namedParameterJdbcTemplate.query(getMalfunctions,hashMap,malfunctionMapper);
    }

    @Override
    public List<Malfunction> getAllMalfunctions() {
        LOGGER.info("DAO: get all malfunctions");
        return namedParameterJdbcTemplate.query(getAllMalfunctions, malfunctionMapper);
    }

    @Override
    public Integer getCostForMalfunctionById(Integer malfunctionId) {
        LOGGER.info("DAO:Get cost of malfunction by id="+malfunctionId.toString());
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(MALFUNCTION_ID.getValue(),malfunctionId);
        return namedParameterJdbcTemplate.queryForObject(getCostById, hashMap, Integer.TYPE);
    }

    @Override
    public Integer getCostForMalfunctionsByApplicationId(Integer applicationId) {
        LOGGER.info("DAO:Get cost for malfunction by application id="+applicationId.toString());
        List<Malfunction> malfunctionList = getAllMalfunctionsByIdApplication(applicationId);
        Integer sum = 0;
        for(Malfunction malfunction:malfunctionList) {
            Integer s = getCostForMalfunctionById(malfunction.getMalfunctionId());
           if(s!=null) sum+=s;
        }
        return sum;
    }

    private MapSqlParameterSource getParametersMap(Malfunction malfunction) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(MALFUNCTION_ID.getValue(), malfunction.getMalfunctionId());
        parameterSource.addValue(NAME.getValue(), malfunction.getName());
        parameterSource.addValue(AUTO.getValue(), malfunction.getAuto());
        parameterSource.addValue(DESCRIPTION.getValue(), malfunction.getDescription());
        parameterSource.addValue(APPLICATION_ID.getValue(), malfunction.getApplicationId());
        return parameterSource;
    }
}
