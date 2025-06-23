//package com.interview.service.config;
//
//import com.interview.service.filter.JWTFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    JWTFilter jwtfilter;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> null; // disables in-memory user loading
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Allow all paths
//                        .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000") // Frontend URL
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("*")
//                        .allowCredentials(true); // If using cookies or Authorization headers
//            }
//        };
//    }
////
//}
