package com.breathalyzer.model;

public class Register {
    
    private int id;
    private String name;
    private String sex;
    private String curp;
    private String plate;
    private double alcohol;
    private double temperature;
    private String viability;
    private String sense;
    private String registration;

    public Register(String name, String sex, String curp, String plate, double alcohol, double temperature, String viability, String sense) {
        this.name = name;
        this.sex = sex;
        this.curp = curp;
        this.plate = plate;
        this.alcohol = alcohol;
        this.temperature = temperature;
        this.viability = viability;
        this.sense = sense;
    }
    
    public Register(int id, String name, String sex, String curp, String plate, double alcohol, double temperature, String viability, String sense, String registration) {
        this(name, sex, curp, plate, alcohol, temperature, viability, sense);
        this.id = id;
        this.registration = registration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getViability() {
        return viability;
    }

    public void setViability(String viability) {
        this.viability = viability;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

}
