package com.intech.hero.configurations;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.intech.hero.services.JWTService;

import lombok.extern.java.Log;

/**
 * JWT Authentification filter
 * 
 * @author sebastien.dupire
 *
 */
@Log
public class JWTAuthenticationFilter extends GenericFilterBean {

	private JWTService jwtService;

	public JWTAuthenticationFilter(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String token = ((HttpServletRequest) request).getHeader("Authorization");
		Authentication authentication = null;
		if (token != null) {
			try {
				String login = jwtService.verify(token);
				log.info("retrieved login : " + login);
				authentication = login != null ? new UsernamePasswordAuthenticationToken(login, null, emptyList())
						: null;
			} catch (Exception e) {
				log.log(Level.SEVERE, "ERROR", e);
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
}
