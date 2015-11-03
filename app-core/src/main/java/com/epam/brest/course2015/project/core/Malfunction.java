package com.epam.brest.course2015.project.core;

public class Malfunction {
    private Integer malfunctionId;
    private String name;
    private String auto;
    private String description;
    private Integer cost;


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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void Malfunction() {
    }
    public void Malfunction(Malfunction malfunction) {
        this.malfunctionId = malfunction.malfunctionId;
        this.name = malfunction.name;
        this.auto = malfunction.auto;
        this.cost = malfunction.cost;
        this.description = malfunction.description;
    }
    public void Malfunction(Integer cost) {
        this.cost = cost;
    }
    public void Malfunction(Integer malfunctionId, String name, String auto, String description) {
        this.malfunctionId = malfunctionId;
        this.name = name;
        this.auto = auto;
        this.description = description;
    }
}
