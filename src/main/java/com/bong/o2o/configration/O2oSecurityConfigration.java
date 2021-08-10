package com.bong.o2o.configration;

import com.bong.o2o.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class O2oSecurityConfigration extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] staticResources  =  {
                "/css/**",
                "/image/**",
                "/",
                "/api"
        };

        http.authorizeRequests()

                .antMatchers(staticResources).permitAll()
                    .anyRequest().authenticated()
                    .and()

                .formLogin()
                    .loginPage("/admin/login")
                    .defaultSuccessUrl("/admin/order")
                    .successHandler(loginSuccessHandler)
                    .permitAll()
                    .and()

                .logout()
                    .permitAll();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

}
