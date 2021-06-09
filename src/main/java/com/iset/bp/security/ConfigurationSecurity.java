/*package com.iset.bp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	public void Config(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("client").password("client").roles("CLIENT");
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.formLogin();
	http.authorizeRequests()
		.antMatchers("/comptes","/consultercompte")
		.hasRole("CLIENT");
	
	}
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
		.csrf().disable()
		.authorizeRequests()
			.anyRequest()
				.authenticated()
					.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.defaultSuccessUrl("/index.html");
	}
}
*/