package com.dherrera.container.service;

import com.dherrera.container.model.Machine;
import com.dherrera.container.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ConfigurationService {

    private Machine large = new Machine("Large", 10);
    private Machine xlarge = new Machine("XLarge", 20);
    private Machine x2large = new Machine("2XLarge", 40);
    private Machine x4large = new Machine("4XLarge", 80);
    private Machine x8large = new Machine("8XLarge", 160);
    private Machine x10large = new Machine("10XLarge", 320);

    private HashMap<Machine, Integer> newYorkConfiguration = new HashMap<>() {
        {
            put(large, 120);
            put(xlarge, 230);
            put(x2large, 450);
            put(x4large, 774);
            put(x8large, 1400);
            put(x10large, 2820);
        }
    };

    private HashMap<Machine, Integer> indiaConfiguration = new HashMap<>() {
        {
            put(large, 140);
            put(x2large, 413);
            put(x4large, 890);
            put(x8large, 1300);
            put(x10large, 2970);
        }
    };

    private HashMap<Machine, Integer> chinaConfiguration = new HashMap<>() {
        {
            put(large, 110);
            put(xlarge, 200);
            put(x4large, 670);
            put(x8large, 1180);
        }
    };

    public HashMap<Machine, Integer> getConfiguration(String location) {
        switch (location) {
            case Constants.NEW_YORK_LOCATION:
                return newYorkConfiguration;
            case Constants.INDIA_LOCATION:
                return indiaConfiguration;
            case Constants.CHINA_LOCATION:
                return chinaConfiguration;
            default:
                return null;
        }
    }
}
