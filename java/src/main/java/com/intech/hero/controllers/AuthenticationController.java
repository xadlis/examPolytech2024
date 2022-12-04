package com.intech.hero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intech.hero.api.LoginDto;
import com.intech.hero.services.JWTService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

/**
 * Authentication controller to permit authentication of an user
 * 
 * @author sebastien.dupire
 *
 */
@Log
@RestController
@RequestMapping("/")
public class AuthenticationController {
	
	@Autowired
	private JWTService jwtService;

	@ApiOperation(value = "Authentication of a random fucking user")
	@PostMapping("authenticate")
	public String authenticate(@RequestBody LoginDto loginDto) {
	    log.info("POST /authenticate");
	    String jws = jwtService.generate(loginDto.getLogin());
	    return jws;
	}
}
