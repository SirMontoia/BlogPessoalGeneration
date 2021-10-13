package com.blogpessoal.Turma29.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecutiryConfig extends WebSecurityConfigurerAdapter {

	private @Autowired 	UserDetailsServiceImplements service;
	
	// Método abaixo, quando utilizado, encripta a senha
	// publico, do tipo serviço encoder com nome senha enconder
	// Retorna novo BCrypt (retorna nova senha encriptada)
	
	@Bean
	public PasswordEncoder senhaEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	// Métodos de segurança que colocamos contra possíveis ataques
	// Permissões de endpoints dentro do nosso código
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		http
		.authorizeRequests()  
			.antMatchers(HttpMethod.PUT, "/api/v1/usuario/atualizar").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/usuario/{id_usuario}").permitAll()
			.antMatchers (HttpMethod.POST, "/api/v1/usuario/salvar").permitAll()
			.antMatchers (HttpMethod.PUT, "/api/v1/usuario/credenciais").permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(service);
		
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(senhaEnconder().encode("porqueporta"))
		.authorities("ROLE_USER");
	}



}



