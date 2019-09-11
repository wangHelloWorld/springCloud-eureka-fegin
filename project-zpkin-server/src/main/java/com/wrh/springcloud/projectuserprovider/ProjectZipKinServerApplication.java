package com.wrh.springcloud.projectuserprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.internal.EnableZipkinServer;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ProjectZipKinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectZipKinServerApplication.class, args);
    }

}
