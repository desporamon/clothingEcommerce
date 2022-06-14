package org.generation.WebProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

    /*
    1) To provide a datasource : Create a object for connection to the database
    so that we can query our Users table. Datasource refers to the URL database, login credential
    to the database (admin/passw0rd#)
    We will need to perform DI (Dependency Injection) - Autowired annotation to an object with
    the datasource, Spring Boot will auto-inject the information from the application.properties
    on the related configuration of the database that it requires
     */

    @Autowired
    private DataSource dataSource;

    /*
    2) Once we have the datasource object, we can now connect to our database, make our query to the users
    table to get the username, password and the role for checking against the user input

    username=? : represents a parameter to be supplied by the client (from the browser) through the Thymeleaf
    Security package
    usersByUsernameQuery : sets the query to be used for finding a user by their username
    authoritiesByUsernameQuery : Sets the query to be used for finding a user's
    authorities by their username
    */

    @Autowired
    public void configAuthentication(
                                      AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?")
        ;
    }

    /*
    3)Using SecurityFilterChain method to customise the views based on authentication. We need to configure
    which views need to login, which views do not require login.
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //Configuration on the security filter, we need to customise (need to do the config on our own
        // based on the requirements)

        //CORS - disable cross-domain restriction for localhost development
        //csrf : Cross-Site Requesy Forgery
        http.csrf().disable();

        //Not using the Spring Security HttpSecurity default login page
        http.formLogin().loginPage("/login");

        //if user successfully login, user will be directed to the productform.html
        http.formLogin()
                .defaultSuccessUrl("/productform");

        //if user successfully logout, user will be directed to the index.html
        http.logout()
                .logoutSuccessUrl("/index");


        /*.antMatchers(......).permitAll() - tells Spring Security that these webpages
         do not need to have login services

        .antMatchers(.....).hasRole("ADMIN") - tells Spring Security that only user
        with ADMIN role will be able to access the productform.html

        logout method : configure logout functionality provided by Spring Security -
        ensure that the login session to be invalidated. Kill the session for us.

        ** - means any internal path can be accessible
        */
        http.authorizeRequests()
                .antMatchers("/", "/products", "/aboutus").permitAll()
                .antMatchers("/productform/**").hasRole("ADMIN")  //ROLE_ADMIN
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

        return http.build();
    }
}

