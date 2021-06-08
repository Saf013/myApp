package org.hillel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/tl/vehicles/", "/tl/vehicles/delete/",
                        "/tl/journeys/", "/tl/stops/", "/tl/stops/delete/", "/tl/stop/save").hasAnyRole("ADMIN")
                .antMatchers("/tl/vehicles/sort", "/tl/vehicles/sorting",
                        "/tl/journeys/sort", "/tl/journeys/sorting", "/tl/stops/sort", "/tl/stops/sorting").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().defaultSuccessUrl("/tl/stops/sort").and()
                .httpBasic(Customizer.withDefaults());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceInDB(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN", "TICKET").build());
        }
        if (!manager.userExists("test")) {
            manager.createUser(User.withUsername("test").password(new BCryptPasswordEncoder().encode("test")).authorities("ROLE_TICKET").build());
        }
        return manager;
    }
}
