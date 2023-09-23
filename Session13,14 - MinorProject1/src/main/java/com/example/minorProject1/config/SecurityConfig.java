package com.example.minorProject1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Value("${users.authority.student}")
    String studentAuthority;

    @Value("${users.authority.admin}")
    String adminAuthority;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/student/").permitAll()
                .antMatchers("/student/**").hasAuthority(studentAuthority)
                .antMatchers("/student_for_admin/**").hasAuthority(adminAuthority)
                .antMatchers("/books/**").hasAnyAuthority(studentAuthority,adminAuthority)
                .antMatchers("saveBook").hasAuthority(adminAuthority)
                .antMatchers("/home").permitAll()
                .and().formLogin();
    }


}
