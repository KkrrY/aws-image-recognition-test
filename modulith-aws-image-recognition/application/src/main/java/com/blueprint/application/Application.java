package com.blueprint.application;

import com.blueprint.common.util.EnvParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.blueprint.*")
@EnableJpaRepositories(basePackages = "com.blueprint.*")
@EntityScan(basePackages = "com.blueprint.*")
public class Application {

    public  static void main(String[] args) {
        boolean isDevEnvironment = Boolean.parseBoolean(System.getenv("DEV_ENVIRONMENT"));
        if (isDevEnvironment) {
            Map<String, String> envVariables = EnvParser.parseEnvFile(".env");
            envVariables.forEach(System::setProperty);
        }

        SpringApplication.run(Application.class, args);
    }

}
