package fr.aleclerc.windpath.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
public class WindpathApp {

    public static void main(String[] args) {
        SpringApplication.run(WindpathApp.class, args);
    }
}