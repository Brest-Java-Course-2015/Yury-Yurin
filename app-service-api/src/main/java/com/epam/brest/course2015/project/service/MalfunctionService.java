package com.epam.brest.course2015.project.service;
import com.epam.brest.course2015.project.core.ApplicationCosts;
import com.epam.brest.course2015.project.core.Malfunction;

import java.util.List;

public interface MalfunctionService {
    Integer addMalfunction(Malfunction malfunction);
    void deleteMalfunction(Integer malfunctionId);
    void updateMalfunction(Malfunction malfunction);
    Malfunction getMalfunctionById(Integer malfunctionId);
    List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId);
    List<Malfunction> getAllMalfunctions();
    List<ApplicationCosts> getMalfunctionsCosts();
    List<ApplicationCosts> getApplicationsCosts();
    void addCostsToMalfunction(Integer malfunctionId, Integer costRepair, Integer costService, Integer additionalExpenses);
}
