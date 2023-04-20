package com.onz.config.security;

import com.onz.common.web.filter.ExceptionFilterHandler;
import com.onz.modules.auth.web.*;
import com.onz.modules.auth.web.JwtAuthenticationRequestFilter;
import com.onz.modules.auth.application.util.JwtProvider;
import com.onz.modules.auth.application.CustomOAuth2UserService;
import com.onz.modules.auth.infra.HttpCookieOAuth2AuthorizationRequestRepository;
import com.onz.modules.auth.application.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final JwtProvider jwtProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .httpBasic().disable()
            .csrf().disable()
            .formLogin().disable()
            .cors().and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/api/admin/login","/api/accounts").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers(HttpMethod.GET, "/download/**").permitAll()
            .antMatchers(HttpMethod.POST, "/login", "/api/accounts").not().fullyAuthenticated()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v3/**",  "/configuration/ui",
                        "/swagger-resources", "/configuration/security",
                        "/swagger-ui/**", "/webjars/**").not().fullyAuthenticated()
                //이부분은 수정해야함 외부노출 xxxx 관리자용 토큰으로 따로 승인받기?
            .antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated();

        http.exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler);

        http.logout()
            .logoutSuccessUrl("/");

        http.oauth2Login()
            .defaultSuccessUrl("/login-success")
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .failureHandler(oAuth2AuthenticationFailureHandler)
            .authorizationEndpoint()
            .baseUri("/oauth2/authorization")
            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
            .and().redirectionEndpoint()
                .baseUri("/login/oauth2/code/*") // OAuth 응답시 처리 설정
            .and()
            .userInfoEndpoint()
            .userService(customOAuth2UserService);

        http.addFilterBefore(new JwtAuthenticationRequestFilter(jwtProvider),
            UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new ExceptionFilterHandler(), JwtAuthenticationRequestFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
//        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

}
