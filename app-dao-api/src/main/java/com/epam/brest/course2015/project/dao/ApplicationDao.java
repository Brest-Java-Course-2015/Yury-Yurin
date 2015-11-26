package com.epam.brest.course2015.project.dao;


import com.epam.brest.course2015.project.core.Application;

import java.util.Date;
import java.util.List;

public interface ApplicationDao {
    Integer addApplication(Application application);
    void deleteApplication(Integer applicationId);
    void updateApplication(Integer applicationId,Date updatedDate);
    Application getApplicationById(Integer applicationId);
    List<Application> getAllApplications();
}
