package com.example.userservice.security;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/actuator/**").permitAll();
		http.authorizeRequests()
			.antMatchers("/**")
			.access("hasIpAddress('" + "172.27.0.96" + "')")
			.and()
			.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService, env);
		// authenticationFilter.setAuthenticationManager(authenticationManager());
		System.out.println("WebSecurity.getAuthenticationFilter");
		return authenticationFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("WebSecurity.configure");
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
}
