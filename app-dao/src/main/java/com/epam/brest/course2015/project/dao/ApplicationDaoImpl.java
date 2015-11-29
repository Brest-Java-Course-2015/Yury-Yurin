package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.Malfunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import static com.epam.brest.course2015.project.core.Application.ApplicationFields.*;
public class ApplicationDaoImpl implements ApplicationDao {

    private RowMapper<Application> applicationMapper = new BeanPropertyRowMapper<Application>() {

        @Override
        public Application mapRow(ResultSet resultSet, int i) throws SQLException {
            Application application = new Application(resultSet.getInt("applicationId"),
                    resultSet.getTimestamp("createdDate"),
                    resultSet.getTimestamp("updatedDate"));
            return application;
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${application.insertApplication}")
    private String addApplication;

    @Value("${application.deleteApplicationById}")
    private String deleteApplicationById;

    @Value("${application.deleteAllMalfunctionByIdApplication}")
    private String deleteAllMalfunctionByIdApplication;

    @Value("${application.getApplications}")
    private String getApplications;

    @Value("${application.updateApplicationById}")
    private String updateApplicationById;

    @Value("${application.getApplicationById}")
    private String getApplicationById;

    @Value("${application.getApplicationsByDate}")
    private String getApplicationsByDate;

    public ApplicationDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Integer addApplication(Application application) {
        LOGGER.info("DAO:Add new application");
        KeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addApplication, getParametersMap(application), key);
        return key.getKey().intValue();
    }

    @Override
    public void deleteApplication(Integer applicationId) {
        LOGGER.info("DAO:Delete application by id="+applicationId.toString());
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(APPLICATION_ID.getValue(), applicationId);
        namedParameterJdbcTemplate.update(deleteApplicationById, hashMap);
        namedParameterJdbcTemplate.update(deleteAllMalfunctionByIdApplication, hashMap);
    }

    @Override
    public void updateApplication(Integer applicationId,Date updatedDate) {
        LOGGER.info("DAO:Update application by id=",applicationId.toString());
            HashMap<String,Object> hashMap = new HashMap<String, Object>();
            hashMap.put(APPLICATION_ID.getValue(),applicationId);
            hashMap.put(UPDATED_DATE.getValue(),updatedDate);
            namedParameterJdbcTemplate.update(updateApplicationById, hashMap);
    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        LOGGER.info("DAO:Get application by id=",applicationId);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put(APPLICATION_ID.getValue(), applicationId);
        return namedParameterJdbcTemplate.queryForObject(getApplicationById,hashMap,applicationMapper);
    }

    @Override
    public List<Application> getAllApplications() {
        LOGGER.info("DAO:Get list of applications");
        return namedParameterJdbcTemplate.query(getApplications, applicationMapper);
    }

    @Override
    public List<Application> getApplicationsBySetDate(Date minDate, Date maxDate) {
        LOGGER.info("DAO:Get list of applications by set Date" + minDate.toString() + " To " + maxDate.toString());
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("minDate", minDate);
        hashMap.put("maxDate", maxDate);
        return namedParameterJdbcTemplate.query(getApplicationsByDate, hashMap,applicationMapper);
    }

    private MapSqlParameterSource getParametersMap(Application application) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(APPLICATION_ID.getValue(), application.getApplicationId());
        parameterSource.addValue(CREATED_DATE.getValue(), application.getCreatedDate());
        parameterSource.addValue(UPDATED_DATE.getValue(), application.getUpdatedDate());
        return parameterSource;
    }
}
