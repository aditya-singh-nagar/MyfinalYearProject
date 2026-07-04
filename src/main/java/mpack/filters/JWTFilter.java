package mpack.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mpack.services.CustomUserDetailsService;
import mpack.utilities.JwtUtility;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtility utility;
	
	@Autowired
	private CustomUserDetailsService custom;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authToken = request.getHeader("Authorization");
		String token = null;
		String email = null;
		
		if(authToken != null && authToken.startsWith("Bearer ")) {
			
			token = authToken.substring(7);
			email = utility.extractEmail(token);
			
			if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				
				UserDetails user = custom.loadUserByUsername(email);
				
				if(utility.checkTokenValidation(token, user.getUsername())) {
					
					UsernamePasswordAuthenticationToken mainToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					
					SecurityContextHolder.getContext().setAuthentication(mainToken);
				}
			}


			
		}
		
		filterChain.doFilter(request, response);
		
		
		
		
	}

}
