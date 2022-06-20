package com.example.triple_be_homework.config.security;



import com.example.triple_be_homework.auth.exception.RestAuthenticationEntryPoint;
import com.example.triple_be_homework.auth.filter.TokenAuthenticationFilter;
import com.example.triple_be_homework.auth.handler.TokenAccessDeniedHandler;
import com.example.triple_be_homework.auth.service.CustomUserDetailsService;
import com.example.triple_be_homework.auth.token.AuthTokenProvider;
import com.example.triple_be_homework.config.properties.AppProperties;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private AuthTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TokenAccessDeniedHandler tokenAccessDeniedHandler;

    /*
     * UserDetailsService 설정
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                .addFilter(corsFilter)
                .authorizeRequests()
                //.antMatchers("/api/support/limit").permitAll()
                //.antMatchers("/api/member/**").hasAnyAuthority(RoleType.MEMBER.getCode())
                //.antMatchers("/api/admin/**").hasAnyAuthority(RoleType.LEAD.getCode() , RoleType.CORE.getCode())
                .anyRequest().permitAll()
                .and();

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /*
     * auth 매니저 설정
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * security 설정 시, 사용할 인코더 설정
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 토큰 필터 설정
     * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * 쿠키 기반 인가 Repository
     * 인가 응답을 연계 하고 검증할 때 사용.
     * */






}