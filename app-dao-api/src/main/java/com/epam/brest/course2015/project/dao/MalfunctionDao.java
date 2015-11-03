package com.epam.brest.course2015.project.dao;

import com.epam.brest.course2015.project.core.Malfunction;

public interface MalfunctionDao {
    public Integer addMalfunction(Malfunction malfunction);
    public void deleteMalfunction(Integer malfunctionId);
    public void updateMalfunction(Malfunction malfunction);
    public Malfunction getMalfunctionById(Integer malfunctionId);
}
