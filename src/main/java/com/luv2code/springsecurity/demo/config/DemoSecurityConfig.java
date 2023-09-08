package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//add our users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("test123").roles("EMPLOYEE","MANAGERS"))
			.withUser(users.username("susan").password("test123").roles("EMPLOYEE","ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//we're going to restrict access based on the HttpServletRequest coming in
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGERS")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.antMatchers("/css/**").permitAll() //enable static resources loaded without authentication
			.anyRequest().authenticated() //any request to the app must be authenticated (ie logged in)
			.and().formLogin()
			.loginPage("/showMyLoginPage") //show our custom form at the request mapping
			.loginProcessingUrl("/authenticateTheUser") //Login form should POST data to this URL for processing - no need to implement this endpoint, Spring supply it freely
			.permitAll()//allow everyone to see login page
			.and()
			.logout().permitAll()//add logout support (enable endpoint POST /logout)
			.and().exceptionHandling().accessDeniedPage("/access-denied");
	}

}
