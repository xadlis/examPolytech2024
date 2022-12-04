package com.intech.hero.services;

import org.springframework.stereotype.Service;

import com.intech.hero.api.FightResponse;
import com.intech.hero.api.FightStepDto;
import com.intech.hero.converters.SuperheroConverter;
import com.intech.hero.errors.exceptions.HeroNotFoundException;
import com.intech.hero.model.Superhero;
import com.intech.hero.repositories.SuperheroRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class FightService {

    private final SuperheroRepository superheroRepository;

    private final SuperheroConverter superheroConverter;

    public FightService(SuperheroRepository superheroRepository, SuperheroConverter superheroConverter){
        this.superheroRepository = superheroRepository;
        this.superheroConverter = superheroConverter;
    }

    public FightResponse startSuperheroesFight(Long idHero1, Long idHero2){
        Superhero fighter1 = this.superheroRepository.findById(idHero1)
                .orElseThrow(() -> new HeroNotFoundException(idHero1));

        Superhero fighter2 = this.superheroRepository.findById(idHero2)
                .orElseThrow(() -> new HeroNotFoundException(idHero2));

        double fighter1PV = fighter1.getDurability(), fighter2PV = fighter2.getDurability();
        int counter = 1;
        List<FightStepDto> steps = new ArrayList<>();
        while(fighter1PV > 0 && fighter2PV > 0){
            double fighter1Damages = Math.round(fighter1.getBaseDamages() * new Random().nextDouble() * 100) / 100.0;
            double fighter2Damages = Math.round(fighter2.getBaseDamages() * new Random().nextDouble() * 100) / 100.0;
            steps.add(new FightStepDto(counter, fighter1Damages, fighter2Damages));

            fighter1PV -= fighter2Damages;
            fighter2PV -= fighter1Damages;
            counter++;
        }

        FightResponse response = new FightResponse();
        response.setFighters(Arrays.asList(
                superheroConverter.convertToMember(fighter1),
                superheroConverter.convertToMember(fighter2)));
        response.setSteps(steps);

        if(fighter1PV>0 && fighter2PV<0){
            response.setWinner(this.superheroConverter.convertToMember(fighter1));
        } else if(fighter1PV<0 && fighter2PV>0){
            response.setWinner(this.superheroConverter.convertToMember(fighter2));
        }

        return response;
    }

}
