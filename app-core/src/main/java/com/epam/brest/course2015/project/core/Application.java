package com.epam.brest.course2015.project.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Application {

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    private Integer applicationId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "GMT")
    private Date createdDate = new Date();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss",timezone = "GMT")
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

    @Override
    public String toString() {
        return String.format("Application : {applicationId:" + applicationId + ", createdDate: " +
                DATE_FORMAT.format(createdDate) + ", updatedDate: " +
                DATE_FORMAT.format(updatedDate) + "}");
    }

    public static enum ApplicationFields {

        APPLICATION_ID ("applicationId"),
        CREATED_DATE ("createdDate"),
        UPDATED_DATE ("updatedDate");

        private ApplicationFields(String value){
            this.value = value;
        }

        private final String value;

        public String getValue(){return value;}

    }
}
