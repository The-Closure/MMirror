package org.closure.MMirror.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@Order(1)
@EnableWebSecurity
// @EnableOAuth2Client
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(getPasswordEncoder());
		
		return authProvider;
	}


    @Autowired
    @Qualifier("oauth2authSuccessHandler")
    private AuthenticationSuccessHandler oauth2authSuccessHandler;

    protected void configure(HttpSecurity http) throws Exception {
      
        http.cors().disable().csrf().disable().formLogin().loginPage("/login").and()
        .logout().logoutSuccessUrl("/login")
                .and().oauth2Login().loginPage("/login")
                .successHandler(oauth2authSuccessHandler);

        http.headers().frameOptions().disable();
    }

   
    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        DelegatingPasswordEncoder encoder =  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}
