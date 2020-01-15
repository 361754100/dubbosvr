package com.hero.demo.dubbosvr.service;

import com.hero.demo.dubbosvr.api.GreetingService;
import com.hero.demo.dubbosvr.api.impl.GreetingServiceImpl;
import com.hero.demo.dubbosvr.config.PropsConfig;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ProviderService {

    private static Logger logger = LoggerFactory.getLogger(ProviderService.class);

    @Autowired
    private PropsConfig propsConfig;

    public void initGreeting() {
        String zooHost = propsConfig.getZoo_host();
        int zooPort = propsConfig.getZoo_port();

        ServiceConfig<GreetingService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zooHost + ":" + zooPort));
        service.setInterface(GreetingService.class);
        service.setRef(new GreetingServiceImpl());
        service.export();

        logger.info("dubbo service started...");
        try {
            new CountDownLatch(1).await();
        } catch (Exception e) {
            logger.error("dubbo service error...", e);
        }
    }

}
