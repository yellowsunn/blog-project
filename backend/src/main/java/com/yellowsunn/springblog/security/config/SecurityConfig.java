package com.yellowsunn.springblog.security.config;

import com.yellowsunn.springblog.security.filter.CustomAuthenticationProcessingFilter;
import com.yellowsunn.springblog.security.handler.CustomAuthenticationFailureHandler;
import com.yellowsunn.springblog.security.handler.CustomAuthenticationSuccessHandler;
import com.yellowsunn.springblog.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // JSESSIONID 항상 생성

                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/article", "/api/categories", "/api/comments/history").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/article", "/api/categories",
                        "/api/header", "/api/profile", "/api/coverCategoryId").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/article/**", "/api/categories/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/authority").authenticated()
                .anyRequest().permitAll()

                .and().logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler((request, response, authentication) -> {})
                .deleteCookies("JSESSIONID")

                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint) // 인증 실패

                .and().formLogin()
                .and().cors().configurationSource(corsConfigurationSource())
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().addFilterBefore(authenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AbstractAuthenticationProcessingFilter authenticationProcessingFilter() throws Exception {
        CustomAuthenticationProcessingFilter authenticationProcessingFilter = new CustomAuthenticationProcessingFilter();
        authenticationProcessingFilter.setAuthenticationManager(super.authenticationManagerBean());
        authenticationProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authenticationProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authenticationProcessingFilter.setSessionAuthenticationStrategy(sessionStrategy());
        return authenticationProcessingFilter;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SessionAuthenticationStrategy sessionStrategy() {
        return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
    }
}
