package com.epam.brest.course2015.project.core;

import java.util.Date;

public class Application {

    private Integer applicationId;
    private Date createdDate = new Date();
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


    public enum ApplicationFields {

        APPLICATION_ID ("applicationId"),
        CREATED_DATE ("createdDate"),
        UPDATED_DATE ("updatedDate");

        ApplicationFields( String value ){
            this.value = value;
        }

        private final String value;

        public String getValue(){return value;}

    }
}
