package com.intech.hero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intech.hero.model.Superhero;

public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

}
