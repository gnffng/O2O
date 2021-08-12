package com.bong.o2o.configration;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.handler.LoginSuccessHandler;
import com.bong.o2o.repository.admin.SpringAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
                "/api",
                "/admin/signUp",
                "/admin/login"
        };

        http.authorizeRequests()

                .antMatchers(staticResources)
                    .permitAll()
                .antMatchers("/admin/**")
                    .hasRole("ADMIN")

                .anyRequest().authenticated()
                    .and()

                .formLogin()
                    .loginPage("/admin/login")
                    .defaultSuccessUrl("/admin/order")
                    .successHandler(loginSuccessHandler)
                    .permitAll()
                    .and()

                .logout()
                    .permitAll()
                    .and()

                .cors()
                    .and()
                .csrf()
                    .disable();

    }

    @Service
    public class BackedLoginService implements UserDetailsService{
        @Autowired
        SpringAdminRepository springAdminRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Admin admin = springAdminRepository.findById(username).orElseThrow(
                    () -> new IllegalArgumentException("ID를 확인해주십시오.")
            );
            return new User(admin.getId(), admin.getPassword(), Arrays.asList(new SimpleGrantedAuthority(admin.getRole())));
        }
    }

    @Bean
    public PasswordEncoder passwordEncoding(){ return new PasswordEncoding(); }

    public class PasswordEncoding implements PasswordEncoder {
        private PasswordEncoder passwordEncoder;

        public PasswordEncoding() {
            this.passwordEncoder = new BCryptPasswordEncoder();
        }

        public PasswordEncoding(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public String encode(CharSequence rawPassword) {
            return passwordEncoder.encode(rawPassword);
        }

        @Override public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return passwordEncoder.matches(rawPassword, encodedPassword);
        }
    }

}
