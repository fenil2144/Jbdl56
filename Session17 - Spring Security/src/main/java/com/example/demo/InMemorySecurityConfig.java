package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class InMemorySecurityConfig
//        extends WebSecurityConfigurerAdapter
{
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("karan").password("karan123")
//                .authorities("dev")
//                .and()
//                .withUser("ram").password("ram123").authorities("test");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                .antMatchers("/developercode/**").hasAuthority("dev")
//                .antMatchers("/testcode/**").hasAuthority("test")
//                .antMatchers("/generalcode").hasAnyAuthority("dev","test")
//                .antMatchers("/home").permitAll().and().formLogin();
//    }
//
//
//    @Bean
//    PasswordEncoder getPE(){
//        return NoOpPasswordEncoder.getInstance();
//    }

}
