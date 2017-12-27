package fr.aleclerc.windpath.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//
                .anyRequest()//
                // .permitAll()//
                .authenticated()//
                .and()//
                .oauth2Login()//
                .and()//
                .logout()//
                .logoutUrl("/logout")//
                .logoutSuccessUrl("/")//
                .and()
                .csrf()//
                .disable()
                .exceptionHandling()//
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        ;

    }
}
