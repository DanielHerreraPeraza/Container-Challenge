package com.dherrera.container.model;

import java.util.Map;

public class RegionAllocation {
    private String regionName;
    private int totalCost;
    private Map<String, Integer> allocatedMachines;

    public RegionAllocation(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Map<String, Integer> getAllocatedMachines() {
        return allocatedMachines;
    }

    public void setAllocatedMachines(Map<String, Integer> allocatedMachines) {
        this.allocatedMachines = allocatedMachines;
    }
}
