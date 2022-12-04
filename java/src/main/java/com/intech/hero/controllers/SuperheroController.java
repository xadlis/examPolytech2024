package com.intech.hero.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.intech.hero.api.SuperheroDto;
import com.intech.hero.api.SuperheroUpdateDto;
import com.intech.hero.services.SuperheroService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/superheroes")
public class SuperheroController {

	@Autowired
	private SuperheroService superheroService;

	@ApiOperation(value = "Retrieve the full heroes list")
	@GetMapping
	public List<SuperheroDto> getAllSuperHeroes(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		log.info("GET /superheroes");
		return superheroService.findAllSuperHeroes(pageable);
	}

	@ApiOperation(value = "Retrieve information about a specific superhero")
	@GetMapping(value = "/{id}")
	public @ResponseBody SuperheroDto getASuperHero(@PathVariable Long id) {
		log.info("GET /superheroes/" + id);
		return superheroService.findSuperHeroById(id);
	}

	@ApiOperation(value = "Update information of a specific superhero")
	@PutMapping(value = "/{id}")
	public void updateASuperHero(@PathVariable Long id, @RequestBody @Valid SuperheroUpdateDto superHero) {
		log.info("PUT /superheroes/" + id);
		superheroService.updateSuperHero(id, superHero);
	}

	@ApiOperation(value = "Create a superhero")
	@PostMapping
	public @ResponseBody SuperheroDto createASuperHero(@RequestBody @Valid SuperheroDto superHero) {
		log.info("POST /superheroes/");
		return superheroService.createSuperHeroLight(superHero);
	}

	@ApiOperation(value = "Delete a specific superhero")
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteASuperHero(@PathVariable Long id, @RequestBody @Valid SuperheroDto superHero) {
		log.info("DELETE /superheroes/" + id);
		superheroService.deleteSuperHeroById(id);
	}

	@ApiOperation(value = "Delete a specific superhero")
	@DeleteMapping(value = "/{id}")
	public void deleteASuperHeroById(@PathVariable Long id) {
		log.info("DELETE /superheroes/" + id);
		superheroService.deleteSuperHeroById(id);
	}

	@GetMapping("/stream")
	public List<SuperheroDto> streams(Pageable pageable) {
		List<SuperheroDto> heroes = superheroService.findAllSuperHeroes(pageable);
		return heroes.stream().filter(sh -> sh.getHeroName().contains("girl")).collect(Collectors.toList());
	}

}
