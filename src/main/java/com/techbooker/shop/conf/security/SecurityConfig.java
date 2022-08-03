package com.techbooker.shop.conf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.techbooker.shop.util.contance.ControllerConstance.API_V1;
import static com.techbooker.shop.util.contance.ControllerConstance.PUBLIC;

//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/api/v1/**").permitAll()
//                .anyRequest().authenticated();
//        http.csrf().disable();
//    }
//}
