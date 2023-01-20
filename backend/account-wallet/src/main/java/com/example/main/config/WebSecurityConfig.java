package com.example.main.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.example.main.filters.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
//	private DataSource dataSource;

//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

	@Autowired
	UserDetailsService service;
	
//	@Bean
//	public InMemoryUserDetailsManager  users(DataSource dataSource) throws Exception {
//		UserDetails user = User.builder()
//				.username("test")
//				.password("$2a$12$Ae3NJl6L38TWfYpX6mElC.6jQfzlVbBNEQJ/cGjt18u0cSZwUSFFa")
//				.roles("USERa")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    		
    	http.cors().and().authorizeHttpRequests()
    		.requestMatchers("/account/**").authenticated()
    		.requestMatchers("/global/**").authenticated()
    		.requestMatchers("/personal/**").authenticated()
    		.requestMatchers("/**").permitAll()
    		.and().csrf().disable()
    		.sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
    		.userDetailsService(service);
    	
    	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    		return http.build();
    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
////        UserDetails user = service.loadUserByUsername();
////        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
////        users.createUser(user);
////        new UserDetailsManager().;
//    	return null;
//    }

    @Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
