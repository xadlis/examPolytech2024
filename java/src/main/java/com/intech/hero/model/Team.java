package com.intech.hero.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.util.List;

@Entity(name = "teams")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Team {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idTeam;

	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "hero_teams",
			joinColumns = @JoinColumn(name = "id_team", referencedColumnName = "idTeam"),
			inverseJoinColumns = @JoinColumn(name = "id_hero", referencedColumnName = "idHero"))
	private List<Superhero> members;

}
