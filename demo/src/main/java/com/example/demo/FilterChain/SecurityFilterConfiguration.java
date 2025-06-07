package com.example.demo.FilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfiguration {
    public SecurityFilterConfiguration() {
        System.out.println("Http Security Filter Chaing");
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public org.springframework.security.web.SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return  httpSecurity.csrf( csrf ->csrf.disable()).
                authorizeHttpRequests( request -> request.
                        requestMatchers("/Public/**").permitAll().
                        requestMatchers("/Admin/**").hasRole("ADMIN").requestMatchers("/User/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().
                        authenticated()).
               //formLogin(Customizer.withDefaults()).
                httpBasic(Customizer.withDefaults()).
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                build();
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService()
    {
        List<UserDetails> li = new ArrayList<>();
        UserDetails us1 = User.withDefaultPasswordEncoder().username("mano").password("123").build();
        UserDetails us2 = User.withDefaultPasswordEncoder().username("vino").password("478").build();
        UserDetails us3 = User.withDefaultPasswordEncoder().username("Raj").password("328").build();
        li.add(us1);li.add(us2); li.add(us3);

        return  new InMemoryUserDetailsManager(li);

    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
       // daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }



}
