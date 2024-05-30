package com.rungroop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c->c.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/register", "/login","/css/**","/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/login") // trang hien form
                                .defaultSuccessUrl("/clubs?successLogin=true")
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error=true")
                                .permitAll()
                )
                .logout(
                        logout -> logout.logoutRequestMatcher(
                                new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout=true").permitAll()
                );
        return http.build();
    }
}
// trong spring securoity măc đinh khi xử lý đăng nhập là /login , mặc định thoát là /logout
// có thể cấu hình lại đăng nhập và thoát khoc thông qua loginProcessurl hoặc logoutprocessurl

