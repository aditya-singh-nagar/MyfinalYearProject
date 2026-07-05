package mpack.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import mpack.filters.JWTFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JWTFilter Jwtfilter;
	
	@Bean
	public SecurityFilterChain setSecurity (HttpSecurity sec) {
		
	return	sec.csrf(a-> a.disable())
			.cors(Customizer.withDefaults())
		.authorizeHttpRequests(req-> req.requestMatchers("/login","/register","/allProducts","/allProductsTwo","/allProductsLast","/").permitAll().anyRequest().authenticated())
		.addFilterBefore(Jwtfilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(a-> a.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.build() ;
		
	}
	
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder(10) ;
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500" ,  "https://your-vercel-app.vercel.app" ,  "https://your-netlify-app.netlify.app" ,  "https://myfinalyearproject-production.up.railway.app"));

	    configuration.setAllowedMethods(List.of(
	            "GET",
	            "POST",
	            "PATCH",
	            "PUT",
	            "DELETE",
	            "OPTIONS"
	    ));

	    configuration.setAllowedHeaders(List.of("*"));

	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
	
	


	
	


	
	

}
