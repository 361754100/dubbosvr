package com.hero.demo.dubbosvr.threadpool;

import com.hero.demo.dubbosvr.common.SpringContext;
import com.hero.demo.dubbosvr.service.ProviderService;

public class InitTask implements Runnable {
    @Override
    public void run() {
        ProviderService providerService = SpringContext.getBean(ProviderService.class);
        providerService.initGreeting();
    }
}
