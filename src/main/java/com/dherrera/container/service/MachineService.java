package com.dherrera.container.service;

import com.dherrera.container.model.Machine;
import com.dherrera.container.model.RegionAllocation;
import com.dherrera.container.model.SampleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MachineService {

    @Autowired
    private ConfigurationService configurationService;

    public RegionAllocation getOptimizedConfiguration(String location, SampleRequest request) {
        HashMap<Machine, Integer> configuration = configurationService.getConfiguration(location);
        return processRegionAllocation(location, request, configuration);
    }

    private List<Machine> sortMachineConfigurationByCost(HashMap<Machine, Integer> configuration) {
        List<Machine> sortedMachineConfiguration = new ArrayList<>();

        configuration.entrySet().stream()
                .sorted(Comparator.comparing(this::getCostPerUnit))
                .forEach((entry) -> {
                    sortedMachineConfiguration.add(entry.getKey());
                });
        return sortedMachineConfiguration;
    }

    private double getCostPerUnit(Map.Entry<Machine, Integer> t) {
        return Double.valueOf(t.getValue() / t.getKey().getUnits());
    }

    private RegionAllocation processRegionAllocation(String location, SampleRequest request, HashMap<Machine, Integer> configuration) {
        List<Machine> sortedMachineConfiguration = sortMachineConfigurationByCost(configuration);
        RegionAllocation regionAllocation = new RegionAllocation(location);
        int unitCount = request.getCapacity();

        int totalCostPerHour = 0;
        Map<String, Integer> machines = new HashMap<>();
        for(Machine machine : sortedMachineConfiguration) {
            int amount = unitCount / (int) machine.getUnits();
            unitCount -= amount * machine.getUnits();

            if(amount > 0) {
                totalCostPerHour += amount * configuration.get(machine);
                machines.put(machine.getName(), amount);
            }
        }
        regionAllocation.setTotalCost(totalCostPerHour * request.getHours());
        regionAllocation.setAllocatedMachines(machines);

        return regionAllocation;
    }
}
