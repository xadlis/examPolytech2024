package com.intech.hero.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intech.hero.api.SuperheroDto;

import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@Log
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

	@ApiOperation(value = "sqsdfdsfffd")
	@GetMapping
	public Iterable<SuperheroDto> getAllSuperHeroes() {
		return null;
	}

}
