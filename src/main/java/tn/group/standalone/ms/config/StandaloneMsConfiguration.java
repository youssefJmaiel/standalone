package tn.group.standalone.ms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"tn.group.standalone.ms"})
@EnableJpaRepositories(basePackages = {"tn.group.standalone.ms.repository"})
public class StandaloneMsConfiguration {
}
