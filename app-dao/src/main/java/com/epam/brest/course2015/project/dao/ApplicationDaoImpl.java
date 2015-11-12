package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.Malfunction;
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
import java.util.HashMap;
import java.util.List;

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
    public ApplicationDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Integer addApplication(Application application) {
        KeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addApplication, getParametersMap(application), key);
        return key.getKey().intValue();
    }

    @Override
    public void deleteApplication(Integer applicationId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("applicationId", applicationId);
        namedParameterJdbcTemplate.update(deleteApplicationById, hashMap);
        namedParameterJdbcTemplate.update(deleteAllMalfunctionByIdApplication, hashMap);
    }

    @Override
    public void updateApplication(Application application) {
            HashMap<String,Object> hashMap = new HashMap<String, Object>();
            hashMap.put("applicationId",application.getApplicationId());
            hashMap.put("createdDate",application.getCreatedDate());
            hashMap.put("updatedDate",application.getUpdatedDate());
            namedParameterJdbcTemplate.update(updateApplicationById, hashMap);
    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("applicationId", applicationId);
        return namedParameterJdbcTemplate.queryForObject(getApplicationById,hashMap,applicationMapper);
    }

    @Override
    public List<Application> getAllApplications() {
        return namedParameterJdbcTemplate.query(getApplications, applicationMapper);
    }

    private MapSqlParameterSource getParametersMap(Application application) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("applicationId", application.getApplicationId());
        parameterSource.addValue("createdDate", application.getCreatedDate());
        parameterSource.addValue("updatedDate", application.getUpdatedDate());
        return parameterSource;
    }
}
