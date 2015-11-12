package com.epam.brest.course2015.project.core;

public class Malfunction {
    private Integer malfunctionId;
    private String name;
    private String auto;
    private String description;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    private Integer applicationId;

    public Integer getCostRepair() {
        return costRepair;
    }

    public void setCostRepair(Integer costRepair) {
        this.costRepair = costRepair;
    }

    public Integer getCostService() {
        return costService;
    }

    public void setCostService(Integer costService) {
        this.costService = costService;
    }

    public Integer getAdditionalExpenses() {
        return additionalExpenses;
    }

    public void setAdditionalExpenses(Integer additionalExpenses) {
        this.additionalExpenses = additionalExpenses;
    }

    private Integer costRepair;
    private Integer costService;
    private Integer additionalExpenses;


    public Integer getMalfunctionId() {
        return malfunctionId;
    }

    public void setMalfunctionId(Integer malfunctionId) {
        this.malfunctionId = malfunctionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Malfunction() {
    }
    public Malfunction(Malfunction malfunction) {
        this.malfunctionId = malfunction.malfunctionId;
        this.name = malfunction.name;
        this.auto = malfunction.auto;
        this.costRepair = malfunction.costRepair;
        this.costService = malfunction.costService;
        this.additionalExpenses = malfunction.additionalExpenses;
        this.description = malfunction.description;
    }
    public Malfunction(Integer costRepair, Integer costService, Integer additionalExpenses)
    {
        this.costRepair = costRepair;
        this.costService = costService;
        this.additionalExpenses = additionalExpenses;
    }
    public Malfunction(Integer malfunctionId, String name, String auto, String description,Integer applicationId) {
        this.malfunctionId = malfunctionId;
        this.name = name;
        this.auto = auto;
        this.description = description;
        this.applicationId = applicationId;
    }
}
