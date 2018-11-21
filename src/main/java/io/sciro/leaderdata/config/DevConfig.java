package io.sciro.leaderdata.config;

import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
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
@EntityScan(basePackages = "io.sciro.leaderdata.entity")
@EnableTransactionManagement
public class DevConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DevConfig.class);

    @Value("${spring.data.neo4j.uri}")
    private String uri;
    @Value("${spring.data.neo4j.username}")
    private String username;
    @Value("${spring.data.neo4j.password}")
    private String password;

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(), "io.sciro.leaderdata.entity");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        LOGGER.debug("URI: \n\n\nuri: {}\n\n\n", uri);
        return new org.neo4j.ogm.config.Configuration
                .Builder()
                .uri(uri)
                .credentials(username, password)
                .build();
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

}

