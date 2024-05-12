package com.htdweb.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;
import java.time.Duration;
import java.util.Arrays;

@Configuration
public class MySecurity {

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select u.username, r.code from users u " +
                "inner join user_role ur on u.id = ur.user_id " +
                "inner join roles r on ur.role_id = r.id where u.username=?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer-> configurer
//                        .requestMatchers("/web/**").hasAnyRole("ADMIN", "SELLER", "CUSTOMER")
                        .requestMatchers("/quan-tri/**").hasAnyRole("ADMIN", "SELLER")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")

                        .anyRequest().permitAll()
        ).formLogin(
                form->form.loginPage("/web/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/web/home")
                        .permitAll()
        ).logout(
                logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
        ).exceptionHandling(
                configurer->configurer.accessDeniedPage("/showPage403")
        )
//                .cors(cors -> cors // Sử dụng cors(Customizer)
//                .configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    // Cấu hình các nguồn gốc được phép truy cập (thay đổi theo tên miền front-end của bạn)
//                    config.setAllowedOrigins(Arrays.asList("http://localhost:8088/web/account1"));
//                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Cho phép các phương thức HTTP thường dùng với Ajax
//                    config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With")); // Cho phép các tiêu đề HTTP cần thiết cho Ajax
//                    config.setMaxAge(Duration.ofHours(1)); // Cho phép lưu trữ bộ nhớ cache cấu hình CORS trong 1 giờ
//                    return config;
//                }));
        ;


        return http.build();
    }

}

