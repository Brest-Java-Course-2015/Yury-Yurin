package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Malfunction;

public interface MalfunctionDao {
    Integer addMalfunction(Malfunction malfunction);
    void deleteMalfunction(Integer malfunctionId);
    void updateMalfunction(Malfunction malfunction);
    Malfunction getMalfunctionById(Integer malfunctionId);
}
