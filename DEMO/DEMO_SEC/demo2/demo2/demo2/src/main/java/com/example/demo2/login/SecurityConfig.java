package com.example.demo2.login;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
    // DataSource
    @Autowired     // 1)  Die Datenquelle via DI verbinden !!!
    private DataSource dataSource;
    // Point 1: password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // SQL für USER UND ROLLEN Abfragen
     private static final String USER_SQL = 
        "SELECT  username, passwd,  true"
        + " FROM  person "
        + " WHERE username = ?";
       // SQL statement to get the role of the user
      private static final String ROLE_SQL = 
         "SELECT  username, role"
        + " FROM  person "
        + " WHERE username = ?";

    // Benutzer aus Login auslesen
    @Override
      protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Get user information at the time of login process from DB
        auth.jdbcAuthentication()
          .dataSource(dataSource)
          .usersByUsernameQuery(USER_SQL)
          .authoritiesByUsernameQuery(ROLE_SQL)
          .passwordEncoder(passwordEncoder());
        ;
      }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // Point 2: Ausnehmen von “static” Resourcen wie Libs und CSS Dateien aus der Security
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        // direct link handling
        http.authorizeRequests()
            .antMatchers("/webjars/**").permitAll() 
            .antMatchers("/css/**").permitAll() 
            .antMatchers("/login").permitAll() 
            .antMatchers("/register").permitAll() 
            .anyRequest().authenticated(); // Andere brauchen eine Anmeldung 
        
        // login    
        http.formLogin()
            .loginProcessingUrl("/login") // Login process path
            .loginPage("/login") // Specify Login Page
            .failureUrl("/login") // Transition destination when login fails
            .usernameParameter("username") // Login.htlm page: user ID Attribut name 
            .passwordParameter("passwd") // Login page password Attribut name
            .defaultSuccessUrl("/home", true); // after successful login
           
        // Logout process
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login");

        // Disable CSRF measures -  dazu später mehr
        http.csrf().disable();
    }
    

}
