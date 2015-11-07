package com.epam.brest.course2015.project.dao;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.MalfunctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.HashMap;

public class MalfunctionDaoImpl implements MalfunctionDao {
    private RowMapper<Malfunction> userMapper = new BeanPropertyRowMapper<Malfunction>(Malfunction.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${malfunctiom.insertMalfunction}")
    private String addMalfunction;

    public MalfunctionDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        KeyHolder key = new GeneratedKeyHolder();
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("malfunctionId",malfunction.getMalfunctionId());
        hashMap.put("name",malfunction.getMalfunctionId());
        hashMap.put("auto",malfunction.getAuto());
        hashMap.put("description",malfunction.getDescription());
        namedParameterJdbcTemplate.update(addMalfunction,hashMap);
        return malfunction.getMalfunctionId();
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {

    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        return null;
    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {

    }
}
