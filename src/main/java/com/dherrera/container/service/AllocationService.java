package com.dherrera.container.service;

import com.dherrera.container.model.RegionAllocation;
import com.dherrera.container.model.Response;
import com.dherrera.container.model.SampleRequest;
import com.dherrera.container.utils.Constants;
import com.dherrera.container.utils.CustomResponseSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllocationService {

    @Autowired
    private MachineService machineService;

    public String processRequest(SampleRequest request) throws JsonProcessingException {
        Response response = calculateCostPlanning(request);
        return serializeResponse(response);
    }

    private Response calculateCostPlanning(SampleRequest request) {
        Response response = new Response();
        List<RegionAllocation> regions = new ArrayList<>();

        for(String region : Constants.REGION_LIST) {
            regions.add(machineService.getOptimizedConfiguration(region, request));
        }

        response.setAllocations(regions);
        return response;
    }

    private String serializeResponse(Response response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomResponseSerializer",
                new Version(1, 0, 0, null, null, null));
        module.addSerializer(Response.class, new CustomResponseSerializer());
        mapper.registerModule(module);

        return mapper.writeValueAsString(response);
    }
}
