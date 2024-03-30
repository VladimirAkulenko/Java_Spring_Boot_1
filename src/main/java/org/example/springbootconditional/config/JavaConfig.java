package org.example.springbootconditional.config;

import org.example.springbootconditional.profile.DevProfile;
import org.example.springbootconditional.profile.ProductionProfile;
import org.example.springbootconditional.profile.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(value = "example.profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "example.profile.dev", havingValue = "false")
    public SystemProfile productionProfile() {
        return new ProductionProfile();
    }
}
