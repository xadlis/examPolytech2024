package com.intech.hero.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.intech.hero.api.MemberDto;
import com.intech.hero.api.SuperheroDto;
import com.intech.hero.model.Superhero;

import java.util.stream.Collectors;

@Component
public final class SuperheroConverter implements Converter<Superhero, SuperheroDto> {

	@Override
	public SuperheroDto convert(Superhero superhero) {
		SuperheroDto dto = new SuperheroDto();
		BeanUtils.copyProperties(superhero, dto);
		dto.setImageSrc("/images/" + superhero.getWebscraperOrder() + "-image.jpg");
		if(superhero.getTeams() != null) {
			dto.setTeams(superhero.getTeams().stream()
			.map(team-> new MemberDto(team.getName(),"/teams/"+team.getIdTeam()))
			.collect(Collectors.toList()));
		}
		return dto;
	}

	public MemberDto convertToMember(Superhero superhero){
		return new MemberDto(superhero.getHeroName(),"/superheroes/"+superhero.getIdHero());
	}

}
