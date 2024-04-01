package io.security.basicsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity
//@Configuration
public class SecurityConfigV10 {

    @Bean
    @Order(0)
    SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/admin/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(
                        withDefaults()
                )
        ;

        return http.build();
    }

    @Bean
    @Order(1)
    SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )
                .formLogin(
                        withDefaults()
                )
        ;

        return http.build();
    }
}
