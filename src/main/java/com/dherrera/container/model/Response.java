package com.dherrera.container.model;

import java.util.List;

public class Response {
    public List<RegionAllocation> allocations;

    public Response() {
    }

    public List<RegionAllocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<RegionAllocation> allocations) {
        this.allocations = allocations;
    }
}
