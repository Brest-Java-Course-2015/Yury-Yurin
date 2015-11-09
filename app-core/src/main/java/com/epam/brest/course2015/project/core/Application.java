package com.epam.brest.course2015.project.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Application {
    private Integer applicationId;
    private List<Integer> malfunctionListId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date updatedDate = new Date();

    public List<Integer> getMalfunctionList() {
        return malfunctionListId;
    }

    public void setMalfunctionList(List<Integer> malfunctionListId) {
        this.malfunctionListId = malfunctionListId;
    }

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

    public Application(Integer applicationId, List<Malfunction> malfunctionList,Date createdDate, Date updatedDate) {
        this.applicationId = applicationId;
        this.malfunctionListId = malfunctionListId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
