package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Malfunction;
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

public class MalfunctionDaoImpl implements MalfunctionDao {
    private RowMapper<Malfunction> malfunctionMapper = new BeanPropertyRowMapper<Malfunction>(Malfunction.class);
    private RowMapper<Integer> malfunctionIntMapper = new RowMapper<Integer>(){

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("malfunctionId");
        }
    };
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    public MalfunctionDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        KeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addMalfunction,getParametersMap(malfunction),key);
        return key.getKey().intValue();
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("malfunctionId",malfunctionId);
        namedParameterJdbcTemplate.update(deleteMalfunctionById,hashMap);
    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("malfunctionId", malfunctionId);
        return namedParameterJdbcTemplate.queryForObject(getMalfunction, hashMap, malfunctionMapper);
    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("malfunctionId",malfunction.getMalfunctionId());
        hashMap.put("name",malfunction.getName());
        hashMap.put("auto",malfunction.getAuto());
        hashMap.put("description",malfunction.getDescription());
        namedParameterJdbcTemplate.update(updateMalfunctionById, hashMap);
    }

    @Override
    public List<Integer> getAllMalfunctionsByIdApplication(Integer applicationId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("applicationId",applicationId);
        return namedParameterJdbcTemplate.query(getMalfunctions,hashMap,malfunctionIntMapper);
    }

    private MapSqlParameterSource getParametersMap(Malfunction malfunction) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("malfunctionId", malfunction.getMalfunctionId());
        parameterSource.addValue("name", malfunction.getName());
        parameterSource.addValue("auto", malfunction.getAuto());
        parameterSource.addValue("description", malfunction.getDescription());
        return parameterSource;
    }
}
