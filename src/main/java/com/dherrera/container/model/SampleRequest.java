package com.dherrera.container.model;

public class SampleRequest {
    private int capacity;
    private int hours;

    public SampleRequest(int capacity, int hours) {
        this.capacity = capacity;
        this.hours = hours;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getHours() {
        return hours;
    }
}
