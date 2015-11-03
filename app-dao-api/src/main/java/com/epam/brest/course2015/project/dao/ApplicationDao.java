package com.epam.brest.course2015.project.dao;


import com.epam.brest.course2015.project.core.Application;

public interface ApplicationDao {
    public void addApplication(Application application);
    public void deleteApplication(Integer applicationId);
    public void updateApplication(Application application);
    public void getApplicationById(Integer applicationId);
}
