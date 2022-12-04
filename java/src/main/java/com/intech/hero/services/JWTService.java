package com.intech.hero.services;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.Base64;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;

/**
 * Service to encode/decode JWT
 * 
 * @author sebastien.dupire
 *
 */
@Log
@Service
public class JWTService {

	@Value("${security.jwt.secret}")
	private String SECRET;

	@Value("${security.jwt.expiration}")
	private int EXPIRATIONTIME;
	
	@Value("${security.jwt.keystore.path}")
	private String KEYSTOREPATH;
	
	@Value("${security.jwt.keystore.password}")
	private String KEYSTOREPASSWORD;
	
	@Value("${security.jwt.keystore.alias}")
	private String KEYSTOREALIAS;
	
	
	public String generate(String login) {
		//return generateHS(login);
		return generateRS(login);
	}
	
	public String verify(String token) {
		//return verifyHS(token);
		return verifyRS(token);
	}
	

	/**
	 * Generate JWT with secret key
	 * 
	 * @param login
	 * @return
	 */
	public String generateHS(String login) {
		log.info("generate HS from : " + login);
		String jwt = null;
		try {			
			jwt = Jwts.builder()
					.setSubject(login)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME*1000))
					.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET.getBytes("UTF-8"))).compact();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		log.info("generated JWT : " + jwt);
		return jwt;
	}
	
	/**
	 * Parse JWT with secret key
	 * 
	 * @param token
	 */
	public String verifyHS(String token) {
		log.info("verify HS : " + token);
		String user = null;
		try {
			user = Jwts.parser()
					.setSigningKey(Base64.getEncoder().encodeToString(SECRET.getBytes("UTF-8")))
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		log.info("user : " + user);
		return user;
	}
	
	
	
	/**
	 * Generate JWT with RSA PrivateKey
	 * 
	 * @param login
	 * @return
	 */
	public String generateRS(String login) {
		log.info("generate RS from : " + login);
		String jwt = null;
		try {			
			KeyStore keystore = KeyStore.getInstance("PKCS12");
			keystore.load(this.getClass().getClassLoader().getResourceAsStream(KEYSTOREPATH), KEYSTOREPASSWORD.toCharArray());
			PrivateKey key = (PrivateKey) keystore.getKey(KEYSTOREALIAS, KEYSTOREPASSWORD.toCharArray());
						
			jwt = Jwts.builder()
					.setSubject(login)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME*1000))
					.signWith(SignatureAlgorithm.RS256, key).compact();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		log.info("generatedRS JWT : " + jwt);
		return jwt;
	}
	
	/**
	 * Parse JWT for RSA certificat
	 * 
	 * @param token
	 */
	public String verifyRS(String token) {
		log.info("verifyRS : " + token);
		String user = null;
		try {
			KeyStore keystore = KeyStore.getInstance("PKCS12");
			keystore.load(this.getClass().getClassLoader().getResourceAsStream(KEYSTOREPATH), KEYSTOREPASSWORD.toCharArray());
			X509Certificate certificate = (X509Certificate) keystore.getCertificate(KEYSTOREALIAS);
			PublicKey pubKey = certificate.getPublicKey();
			
			user = Jwts.parser()
					.setSigningKey(pubKey)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "ERROR", e);
		}
		log.info("user : " + user);
		return user;
	}
}
