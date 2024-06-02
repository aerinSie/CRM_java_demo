package com.demo.crm.crm_java_demo.config;


import com.demo.crm.crm_java_demo.service.SysUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //    a. super user: access to all functions.
//    b. manager: modify/delete/view company/client data.
//    c. operator: create/view company/client data.
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailManager() {
        UserDetails superUser = User
                .withUsername("user1")
                .password(passwordEncoder().encode("user1"))
                .roles("SUPPER_USER")
                .build();

        UserDetails manager = User
                .withUsername("user2")
                .password(passwordEncoder().encode("user2"))
                .roles("MANAGER")
                .build();

        UserDetails operator = User
                .withUsername("user3")
                .password(passwordEncoder().encode("user3"))
                .roles("OPERATOR")
                .build();
        return new InMemoryUserDetailsManager(List.of(superUser, manager, operator));
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SysUserDetailsService();
    }

    //    a. super user: access to all functions.
//    b. manager: modify/delete/view company/client data.
//    c. operator: create/view company/client data.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                ).logout(logout -> logout.logoutUrl("logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                ).authorizeHttpRequests(requests -> requests
                        .requestMatchers("/error/**", "/login","/logout", "/swagger-ui/**", "/v3/api-docs/**",
                                "/swagger-resources/**", "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                ).build();
    }


}


