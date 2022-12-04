package com.intech.hero.services;

import org.springframework.stereotype.Service;

import com.intech.hero.api.TeamDto;
import com.intech.hero.converters.TeamConverter;
import com.intech.hero.errors.exceptions.HeroNotFoundException;
import com.intech.hero.model.Team;
import com.intech.hero.repositories.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService {

	private TeamRepository teamRepository;

	private TeamConverter teamConverter;

	public TeamService(TeamRepository teamRepository, TeamConverter teamConverter){
	    this.teamRepository = teamRepository;
	    this.teamConverter = teamConverter;
    }

	public List<TeamDto> findAllTeams() {
		return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .map(teamConverter::convert)
                .collect(Collectors.toList());
	}

	public TeamDto findTeamById(Long id) {
		return teamRepository.findById(id)
				.map(teamConverter::convert)
				.orElseThrow(()->new HeroNotFoundException(id));
	}

	public void updateTeam(Long id, TeamDto teamDto) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(()->new HeroNotFoundException(id));

        Team modifiedTeam = existingTeam.toBuilder()
                .name(teamDto.getName())
                .build();

        teamRepository.save(modifiedTeam);
	}

}
