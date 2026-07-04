package mpack.utilities;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
	
	
	@Value("${secret}")
	private String secretKey ;
	
	public SecretKey getKey () {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
	
	public String generateToken (String email , String role , String username) {
		
		System.out.println(email);
		
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		claims.put("name", username);
		
		return Jwts.builder()
				.claims(claims)
				.subject(email)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000*60*60))
				.signWith(SignatureAlgorithm.HS256,getKey())
				.compact();
		
	}
	
	public String extractEmail(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public Date getExpiry(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getExpiration();
	}
	
	public boolean checkExpiry(String token) {
		return getExpiry(token).before(new Date(System.currentTimeMillis()));
	}
	
	public boolean checkTokenValidation(String token , String email) {
		
		return !checkExpiry(token) && extractEmail(token).equals(email) ;
		
	}

}
