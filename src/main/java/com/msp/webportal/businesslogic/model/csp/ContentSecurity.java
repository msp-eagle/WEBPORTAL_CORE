package com.msp.webportal.businesslogic.model.csp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ContentSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/aboutComposition/**","/aboutCareers/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()

                .headers()
                .contentSecurityPolicy(
                        "default-src 'self'; " +
                                "img-src 'self' data: https: http:; " +
                                "script-src 'self' 'unsafe-inline' 'unsafe-eval'; " +
                                "style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; " +
                                "font-src 'self' https://fonts.gstatic.com; " +
                                "connect-src 'self'; " +
                                "frame-src 'self';"
                )
                .and()
                .frameOptions().disable();
    }
}

