package com.pension.authorization.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pension.authorization.exception.AuthenticationException;
import com.pension.authorization.model.UserRequest;
import com.pension.authorization.service.MyUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, String>> getAuthenticationToken(@RequestBody UserRequest userRequest) throws Exception,AuthenticationException{
		Map<String,String> authToken= new HashMap<>();
		UserDetails user= myUserService.loadUserByUsername(userRequest.getUsername());
		authToken.put("token", generateJwt(user.getUsername()));
		if(! passwordEncoder.matches(userRequest.getPassword(), user.getPassword()))
			throw new AuthenticationException("Invalid Credentials.");
		return ResponseEntity.status(HttpStatus.OK).body(authToken);
	}
	
	
	@PostMapping(value = "/authorize")
	public boolean authorizeAdmin(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
		String jwtToken = null;
		String userName = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return false;
			}
		}
		return userName != null;

	}
	
	private String generateJwt(String user) {
		JwtBuilder builder=Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date(new Date().getTime()+(30*60*1000)));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token= builder.compact();
		return token;
	}
	
	private String getUsernameFromToken(String token) {
		Jws<Claims> jws=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token.replace("Bearer ", ""));
			String user=jws.getBody().getSubject();
			return user;	
		}
	@GetMapping("/actuator/info")
	public String actuatorInfo() {
		return "Authentication service is up and running";
	}
	}
