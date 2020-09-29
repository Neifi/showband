package com.neifi.showband.security.jwt;

import java.security.Key;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.neifi.showband.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;

@Log
@Component

public class JwtProvider {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";


	private final String jwtSecret  = "A4F41C4CBFC51BC3CC0FBF6654CB9366EF7F87894F15C7C2F85D5F01F64E4B65";
	@Value("${jwt.token-expiration: 3600}")
	private int jwtDurationTokenSecs;
		
	private  final Key secret = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	private  final byte[] secretBytes = secret.getEncoded();
	private  final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	public String generateToken(Authentication authentication) {
		User usuario = (User) authentication.getPrincipal();
		Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDurationTokenSecs * 1000));
		
		return Jwts.builder()
				.setHeaderParam("typ", TOKEN_TYPE).setSubject(Long.toString((usuario.getUserId())))
				.setIssuedAt(new Date()).setExpiration(tokenExpirationDate).claim("fullname", usuario.getUsername())
				.signWith(SignatureAlgorithm.HS512,base64SecretBytes)
				.claim("roles", "ADMIN").compact();//usuario.getRol().toString()).compact();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes())).parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claims.getSubject());
	}

public boolean validateToken(String authToken) {
		
		try {
			Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
			return true;
		
		} catch (MalformedJwtException ex) {
			log.info("Token malformado: " + ex.getMessage());
		} catch (ExpiredJwtException ex) {
			log.info("El token ha expirado: " + ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			log.info("Token JWT no soportado: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			log.info("JWT claims vacío");
		}
        return false;
		
	}
}


