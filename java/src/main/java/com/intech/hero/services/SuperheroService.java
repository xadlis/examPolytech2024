package com.intech.hero.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.intech.hero.api.SuperheroDto;
import com.intech.hero.api.SuperheroUpdateDto;
import com.intech.hero.converters.SuperheroConverter;
import com.intech.hero.errors.exceptions.HeroNotFoundException;
import com.intech.hero.model.Superhero;
import com.intech.hero.repositories.SuperheroRepository;

import lombok.extern.java.Log;

@Log
@Service
public class SuperheroService {

	private SuperheroRepository superheroRepository;

	private SuperheroConverter superheroConverter;
	
	public SuperheroService(SuperheroRepository superheroRepository, SuperheroConverter superheroConverter){
	    this.superheroRepository = superheroRepository;
	    this.superheroConverter = superheroConverter;
    }

	public List<SuperheroDto> findAllSuperHeroes(Pageable pageable) {
		return StreamSupport.stream(superheroRepository.findAll(pageable).spliterator(), false)
                .map(superheroConverter::convert)
                .collect(Collectors.toList());
	}

	public SuperheroDto findSuperHeroById(Long id) {
		return superheroRepository.findById(id)
				.map(superheroConverter::convert)
				.orElseThrow(()->new HeroNotFoundException(id));
	}

	public void updateSuperHero(Long id, @Valid SuperheroUpdateDto superHero) {
		Superhero existingHero = superheroRepository.findById(id).orElseThrow(() -> new HeroNotFoundException(id));

		BeanUtils.copyProperties(superHero, existingHero);
		superheroRepository.save(existingHero);
	}
	
	public void deleteSuperHeroById(Long id) {
		Superhero existingHero = superheroRepository.findById(id)
                .orElseThrow(()->new HeroNotFoundException(id));
		
		superheroRepository.delete(existingHero);
        }
        
        public SuperheroDto createSuperHeroLight(SuperheroDto superheroDto) {
                log.info("createSuperHeroLight : " + superheroDto.getHeroName());
                Superhero result = new Superhero();
		// result.setWebscraperOrder(superheroDto.getHeroName());
                
                //Light form version for formation
                superheroDto.setFullName(superheroDto.getHeroName());
                superheroDto.setAlterEgos("alterEgos");
                superheroDto.setPlaceOfBirth("placeOfBirth");
                superheroDto.setAlignment("good");
                superheroDto.setGender("Female");
                superheroDto.setHeight("height");
                superheroDto.setWeight("weight");
                superheroDto.setEyes("eyes");
                superheroDto.setOccupation("occupation");
                superheroDto.setHistory("Mocked created Hero");
                result = mergeEntity(result, superheroDto);
                return superheroConverter.convert(superheroRepository.save(result));
        }

        public SuperheroDto createSuperHero(SuperheroDto superheroDto) {
                Superhero result = new Superhero();
                result.setWebscraperOrder(superheroDto.getHeroName());
                result = mergeEntity(result, superheroDto);
                return superheroConverter.convert(superheroRepository.save(result));
        }

        public Superhero mergeEntity(Superhero existingHero, SuperheroDto superheroDto) {
                Superhero superhero = existingHero.toBuilder()
                .aliases(superheroDto.getAliases())
                .alignment(superheroDto.getAlignment())
                .alterEgos(superheroDto.getAlterEgos())
                .base(superheroDto.getBase())
                .combat(superheroDto.getCombat())
                .durability(superheroDto.getDurability())
                .equipments(superheroDto.getEquipments())
                .eyes(superheroDto.getEyes())
                .firstAppearance(superheroDto.getFirstAppearance())
                .fullName(superheroDto.getFullName())
                .gender(superheroDto.getGender())
                .hairs(superheroDto.getHairs())
                .height(superheroDto.getHeight())
                .heroName(superheroDto.getHeroName())
                .history(superheroDto.getHistory())
                .intelligence(superheroDto.getIntelligence())
                .occupation(superheroDto.getOccupation())
                .placeOfBirth(superheroDto.getPlaceOfBirth())
                .power(superheroDto.getPower())
                .powers(superheroDto.getPowers())
                .publisher(superheroDto.getPublisher())
                .race(superheroDto.getRace())
                .relatives(superheroDto.getRelatives())
                .speed(superheroDto.getSpeed())
                .strength(superheroDto.getStrength())
                .weapons(superheroDto.getWeapons())
                .weight(superheroDto.getWeight())
                .build();
                return superhero;
         }
}
