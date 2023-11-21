package dev.buga.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(HibernateConfig.class)
@ComponentScan(basePackages = "dev.buga")
@EnableJpaRepositories(basePackages = "dev.buga.data")
public class AppConfig {
}
