package io.security.basicsecurity;

import jakarta.servlet.Filter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootTest
public class SecurityConfigTest {

    @Test
    void getFilter() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SecurityConfigV9.class);

        SecurityFilterChain securityFilterChain = ac.getBean(SecurityFilterChain.class);
        for (Filter filter : securityFilterChain.getFilters()) {
            System.out.println(filter);
        }
    }
}
