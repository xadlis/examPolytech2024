package com.intech.hero.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.intech.hero.api.MemberDto;
import com.intech.hero.api.TeamDto;
import com.intech.hero.model.Team;

import java.util.stream.Collectors;

@Component
public final class TeamConverter implements Converter<Team, TeamDto> {

	@Override
	public TeamDto convert(Team team) {
		TeamDto dto = new TeamDto();
		BeanUtils.copyProperties(team, dto);
		dto.setMembers(team.getMembers().stream()
			.map(member-> new MemberDto(member.getHeroName(),"/superheroes/"+member.getIdHero()))
			.collect(Collectors.toList()));
		return dto;
	}

}
