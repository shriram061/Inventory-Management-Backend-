package com.authentication.einstein.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.authentication.einstein.model.UserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * extractUsername: Extracts the username from the given JWT token.
 * extractExpiration: Extracts the expiration date of the given JWT token.
 * extractClaim: Extracts a specific claim from the given JWT token based on the
 * given claimsResolver function. extractAllClaims: Extracts all claims from the
 * given JWT token. isTokenExpired: Checks whether the given JWT token has
 * expired or not. generateToken: Generates a new JWT token for the given
 * userDetail object. createToken: Creates a new JWT token with the given claims
 * and username. validateToken: Validates the given JWT token by checking its
 * expiration date and whether it belongs to the given userDetail object.
 *
 */
@Service
public class JwtService {

	@Value("authenticationeinstein")
	private String SECRETKEY; // from resourse

	@Value("30")
	private int TOKENDURATION;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		String formated_token = token.trim().replaceAll("\0xfffd", "");
		return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(formated_token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetail userDetail) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetail.getUsername()); // payload
	}

	private String createToken(Map<String, Object> claims, String username) {

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKENDURATION * 30 * 1000))
				.signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();
	}

	public Boolean validateToken(String token, UserDetail userDetail) {
		final String username = extractUsername(token);
		System.out.println(username);
		return (username.equals(userDetail.getUsername()) && !isTokenExpired(token));
	}
}