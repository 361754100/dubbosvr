package com.hero.demo.dubbosvr.api.impl;

import com.hero.demo.dubbosvr.api.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingServiceImpl implements GreetingService {

    private static Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

    @Override
    public String sayHi(String name) {
        logger.debug("sayHi...name = {}", name);
        return "Hi~" + name;
    }
}
