package com.hero.demo.dubbosvr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Descript 自定义配置-实体类映射
 * @Author mojianzhang
 * @Date 2018-07-17 11:15:48
 * @Version 1.0
 */
@Configuration
public class PropsConfig {

    @Value("${zookeeper.host}")
    private String zoo_host;

    @Value("${zookeeper.port}")
    private int zoo_port;

    public String getZoo_host() {
        return zoo_host;
    }

    public void setZoo_host(String zoo_host) {
        this.zoo_host = zoo_host;
    }

    public int getZoo_port() {
        return zoo_port;
    }

    public void setZoo_port(int zoo_port) {
        this.zoo_port = zoo_port;
    }

    @Override
    public String toString() {
        return "PropsConfig{" +
                "zoo_host='" + zoo_host + '\'' +
                ", zoo_port=" + zoo_port +
                '}';
    }
}
