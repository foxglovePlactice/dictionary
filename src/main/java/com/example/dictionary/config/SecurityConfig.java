package com.example.dictionary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.permitAll())
//		.logout(logout -> logout
//				.logoutSuccessUrl("/"))
		.authorizeHttpRequests(authz -> authz
//				.requestMatchers("/css/**").permitAll()
//				.requestMatchers("/").permitAll()
				.requestMatchers("/words/**").permitAll()  //Controllerの@RequestMappingの中身のところから書く
				.requestMatchers("/words").permitAll()
				.requestMatchers("/sample").permitAll()
				.requestMatchers("/wordsAdmin/**").authenticated()
//				.requestMatchers("/detail/**").permitAll()
//				.requestMatchers(HttpMethod.GET).permitAll()
//				.requestMatchers("/admin/**").hasRole("ADMINISTRATOR")
//				.requestMatchers("/edit/**").hasAnyRole("ADMINISTRATOR", "EDITOR")
//				.requestMatchers("/view/**").hasAnyRole("ADMINISTRATOR", "EDITOR", "VIEWER")
				.anyRequest().authenticated()
				);
//		http.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}
    

}
