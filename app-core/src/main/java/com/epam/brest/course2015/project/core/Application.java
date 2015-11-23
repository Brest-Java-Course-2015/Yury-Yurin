package com.epam.brest.course2015.project.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Application {
    private Integer applicationId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdDate = new Date();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date updatedDate;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Application() {

    }

    public Application(Integer applicationId,Date createdDate, Date updatedDate) {
        this.applicationId = applicationId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Application(Application application) {
        this.applicationId = application.applicationId;
        this.createdDate = application.createdDate;
        this.updatedDate = application.updatedDate;
    }
}
