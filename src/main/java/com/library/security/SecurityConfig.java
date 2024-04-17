package com.library.security;

import com.library.exceptions.GlobalExceptionHandler;
import com.library.service.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTTokenFilter jwtTokenFilter;
    private final GlobalExceptionHandler accessDeniedHandler;

    public SecurityConfig(UserDetailsService userDetailsService, JWTTokenFilter jwtTokenFilter, GlobalExceptionHandler accessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                    request -> request.requestMatchers("/login/**", "/register/**")
                            .permitAll()
                            .requestMatchers("/user/**", "/book/add", "book/delete/{id}", "book/update/{id}",
                                    "/loan/add", "/loan/update/{id}", "/loan/getAll").hasAuthority("ADMIN")
                            .anyRequest()
                            .authenticated()
            ).userDetailsService(userDetailsService)
                .exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
