package io.sciro.leaderdata.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * PACKAGE : io.repo.config
 * USER    : Kudzai Sean Huni
 * TIME    : 14:36
 * DATE    : Monday-13-August-2018
 * E-MAIL  : kudzai@bcs.org
 * CELL    : +27-64-906-8809
 */
@Profile("dev")
@Configuration
@EnableNeo4jRepositories(basePackages = "io.sciro.leaderdata.repo")
@EntityScan(basePackages = "io.sciro.leaderdata.domain")
@EnableTransactionManagement
public class DevConfig {
    public DevConfig() {
        //Dev-Config
    }
}
