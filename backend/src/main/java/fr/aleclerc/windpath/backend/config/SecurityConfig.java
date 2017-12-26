package fr.aleclerc.windpath.backend.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//
                .anyRequest()//
                .authenticated()//
                .and()//
                .oauth2Login()//
                .and()//
                .csrf()//
                .disable()//
        ;

    }

//    @Bean
//    public InMemoryClientRegistrationRepository inMemoryClientRegistrationRepository(){
//        return new InMemoryClientRegistrationRepository();
//    }


}
