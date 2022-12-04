package com.intech.hero.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.intech.hero.api.FightResponse;
import com.intech.hero.services.FightService;

@Log
@RestController
@CrossOrigin
@RequestMapping("/fights")
public class FightController {
	
	private final FightService fightService;

	public FightController(FightService fightService){
		this.fightService = fightService;
	}

	@ApiOperation(
			value = "Launch a fight between two superheroes"
	)
	@GetMapping(value = "/{idHero1}/vs/{idHero2}")
	public @ResponseBody
	FightResponse fightSuperheroes(@PathVariable Long idHero1, @PathVariable Long idHero2) {
		log.info("GET /fights/"+idHero1+"/vs/"+idHero2);
		return fightService.startSuperheroesFight(idHero1,idHero2);
	}

}
