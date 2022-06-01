package ru.itis.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import ru.itis.security.filter.JwtTokenAuthenticationFilter;
import ru.itis.security.filter.JwtTokenAuthorizationFilter;
import ru.itis.security.userDetails.TokenAuthenticationUserDetailsService;
import ru.itis.service.jwt.CustomDetailsService;
import ru.itis.service.jwt.JwtTokenService;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenService tokenService;
    private final CustomDetailsService customDetailsService;
    private final TokenAuthenticationUserDetailsService authorizationUserDetailsService;

    private static final String[] PERMIT_ALL = {
            "/",
            "/api/v1/users/login",
            "/api/v1/doctors/sign-up",
            "/api/v1/patients/sign-up",
            "/api/v1/token/user-info",
            "/api/v1/token/refresh",
            "/api/v1/info/**",
            "/v2/api-docs",
            "/webjars/**",
            "/swagger-resources/**",
            "/swagger-ui.html/**"
    };

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiration.sec}")
    private long expirationInSec;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenAuthenticationFilter authenticationFilter =
                new JwtTokenAuthenticationFilter(authenticationManagerBean(),
                        objectMapper, secretKey, expirationInSec);

        JwtTokenAuthorizationFilter authorizationFilter =
                new JwtTokenAuthorizationFilter(objectMapper, secretKey);

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers(PERMIT_ALL).permitAll()
                    .antMatchers("/api/v1/card/**").hasAnyRole("DOCTOR")
                    .antMatchers(HttpMethod.POST, "/api/v1/card/{userId}").access("@userSecurity.hasUserId(authentication, #userId)")
                    .antMatchers("/api/v1/doctors/{userId}").access("@userSecurity.hasUserId(authentication, #userId)")
                    .antMatchers("/api/v1/patients/{userId}").access("@userSecurity.hasUserId(authentication, #userId)")
                    .antMatchers("/api/v1/users/{userId}").access("@userSecurity.hasUserId(authentication, #userId)")
                    .anyRequest().authenticated()
                .and()
                    .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .logout().disable();
    }
}