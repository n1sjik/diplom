package ru.spb.cmt.doctorcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import ru.spb.cmt.doctorcloud.service.PeopleService;
import ru.spb.cmt.doctorcloud.service.PersonService;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSecurity
@EnableSpringHttpSession
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PeopleService peopleService;
    @Autowired
    private PersonService personService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestCache().disable()
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().maximumSessions(1);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    @Bean(name = "peopleAuthentication")
    public AuthenticationProvider peopleAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(peopleService);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean(name = "personAuthentication")
    public AuthenticationProvider personAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(personService);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    private PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    @Bean
    public MapSessionRepository sessionRepository() {
        MapSessionRepository sessionRepository = new MapSessionRepository(new ConcurrentHashMap<>());
        sessionRepository.setDefaultMaxInactiveInterval(86400);
        return sessionRepository;
    }


}
