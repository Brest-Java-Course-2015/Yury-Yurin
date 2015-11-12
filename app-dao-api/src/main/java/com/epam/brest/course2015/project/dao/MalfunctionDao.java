package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Malfunction;

import java.util.List;

public interface MalfunctionDao {
    Integer addMalfunction(Malfunction malfunction);
    void deleteMalfunction(Integer malfunctionId);
    void updateMalfunction(Malfunction malfunction);
    Malfunction getMalfunctionById(Integer malfunctionId);
    List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId);
}
