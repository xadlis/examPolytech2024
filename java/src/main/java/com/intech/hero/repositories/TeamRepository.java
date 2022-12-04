package com.intech.hero.repositories;

import org.springframework.data.repository.CrudRepository;

import com.intech.hero.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
