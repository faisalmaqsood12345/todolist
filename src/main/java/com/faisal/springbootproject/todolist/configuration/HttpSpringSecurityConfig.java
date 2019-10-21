package com.faisal.springbootproject.todolist.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity
public class HttpSpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception
	{		
		@SuppressWarnings( "deprecation" )
		UserBuilder users = User.withDefaultPasswordEncoder();
		//add users for in memory authentication
		auth.inMemoryAuthentication()
			.withUser(users.username( "test" ).password( "pwd123" ).roles( "ADMIN" ) ) 
			.withUser(users.username( "test1" ).password( "test123" ).roles( "MANAGER" ) )
			.withUser(users.username( "sa" ).password( "admin" ).roles( "MANAGER" ) )
			.withUser(users.username( "test2" ).password( "test123" ).roles( "MANAGER" ) );
	}

	@Override
	protected void configure( HttpSecurity http ) throws Exception 
	{
		http.authorizeRequests()
			.antMatchers( "/todolists/list*" ).hasAnyRole( "MANAGER", "ADMIN" )
			.antMatchers( "/todolists/toDoListFormAdd*" ).hasAnyRole( "MANAGER", "ADMIN" )
			.antMatchers( "/todolists/delete" ).hasRole( "ADMIN" )
			.antMatchers( "/todolists/**" ).hasAnyRole( "MANAGER", "ADMIN" )
			.antMatchers( "/resources/**" ).permitAll()
			.and()
			.formLogin()
			.and()
			.exceptionHandling().accessDeniedPage( "/access-denied" );
		     http.csrf().ignoringAntMatchers( "/h2/**" );
		     http.headers().frameOptions().sameOrigin();
	}
}
