package com.epam.brest.course2015.project.dao;


import com.epam.brest.course2015.project.core.Application;

public interface ApplicationDao {
    void addApplication(Application application);
    void deleteApplication(Integer applicationId);
    void updateApplication(Application application);
    void getApplicationById(Integer applicationId);
}
