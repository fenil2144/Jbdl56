package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeHttpRequests()
                .antMatchers("/developercode/**").hasAuthority("dev")
                .antMatchers("/testcode/**").hasAuthority("test")
                .antMatchers("/generalcode").hasAnyAuthority("dev","test")
                .antMatchers("/home").permitAll().and().formLogin();
    }




    //Role Based Authority: Ant Matchers will have
    //multiple roles for set of apis and user in db will have single role

    //Action based Authority: Ant Matchers will have Single Role for a Set of Api's and
    // user in db will have multiple actions in Authority column.

    //Combination of Role and Action Based

}
