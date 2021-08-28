package net.problem.appforkrit.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("net.problem.appforkrit.domain.entities")
@EnableJpaRepositories(basePackages = {"net.problem.appforkrit.domain.repositories"})
public class DatabaseConfig {
}
