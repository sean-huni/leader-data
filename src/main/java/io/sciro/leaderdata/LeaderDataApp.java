package io.sciro.leaderdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * PROJECT   : leader-data
 * PACKAGE   : io.sciro.leaderdata
 * USER      : sean
 * DATE      : 17-Mon-Sep-2018
 * TIME      : 16:17
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = {"io.sciro.leaderdata.config", "io.sciro.leaderdata.repo", "io.sciro.leaderdata.domain", "io.sciro.leaderdata.controller"})
public class LeaderDataApp {
    public static void main(String[] args) {
        SpringApplication.run(LeaderDataApp.class, args);
    }
}
