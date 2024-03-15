package io.security.basicsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity
//@Configuration
public class SecurityConfigV7 {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}1111")
                .roles("USER")
                .build();

        UserDetails sys = User.builder()
                .username("sys")
                .password("{noop}1111")
                .roles("SYS", "USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}1111")
                .roles("ADMIN", "SYS", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, sys, admin);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin/pay").hasRole("ADMIN")
                        .requestMatchers("/admin/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('SYS')"))
                        .anyRequest().authenticated()
                )
                .formLogin(
                        withDefaults()
                )
        ;

        return http.build();
    }
}
