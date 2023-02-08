package edu.miu.cs.cs425.student.config;

import edu.miu.cs.cs425.student.service.ERegistrarUserDetailsService;
//import edu.mum.cs.cs425.demos.elibrary_springsec_1.service.ElibraryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(ERegistrarUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/resources/static/**", "/images/**", "/css/**", "/elibrary/public/**").permitAll()
                .antMatchers("/", "/elibrary").permitAll()
                .antMatchers("/elibrary/secured/services/admin/**").hasRole("ADMIN")
                .antMatchers("/elibrary/secured/services/librarian/**").hasRole("LIBRARIAN")
                .antMatchers("/elibrary/secured/services/libmember/**").hasRole("LIBMEMBER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/elibrary/public/login")
                .defaultSuccessUrl("/elibrary/secured/home")
                .failureUrl("/elibrary/public/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/elibrary/public/logout"))
                .logoutSuccessUrl("/elibrary/public/login?logout")
                .permitAll()
                .and()
                .exceptionHandling();
    }

}
