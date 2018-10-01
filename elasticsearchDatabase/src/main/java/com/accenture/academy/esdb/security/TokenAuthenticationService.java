package com.accenture.academy.esdb.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenAuthenticationService {
	
	static final long EXPIRATIONTIME = 864_000_000; //10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	static void addAuthentication(HttpServletResponse res, String username) {
		String token = JWT.create()
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.sign(HMAC512(SECRET.getBytes()));
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
	    String token = request.getHeader(HEADER_STRING);
	    if (token != null) {
	      // parse the token.
	      String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
	          .build()
	          .verify(token.replaceAll(TOKEN_PREFIX, ""))
	          .getSubject();

	      return user != null ?
	          new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
	          null;
	    }
	    return null;
	}

}
