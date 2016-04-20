package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.Application;

import java.util.Date;
import java.util.List;

public interface ApplicationService {
    Integer addApplication(Application application);
    void deleteApplication(Integer applicationId);
    void updateApplication(Integer applicationId,String updatedDate);
    Application getApplicationById(Integer applicationId);
    List<Application> getAllApplications();
    List<Application> getAllApplicationsByDate(String minDate,String maxDate);
}
