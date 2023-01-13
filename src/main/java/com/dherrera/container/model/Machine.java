package com.dherrera.container.model;

public class Machine {
    private String name;
    private double units;

    public Machine(String name, double units) {
        this.name = name;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public double getUnits() {
        return units;
    }
}
