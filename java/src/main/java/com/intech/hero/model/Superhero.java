package com.intech.hero.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hero")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Superhero {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idHero;

	private String fullName;

	private String placeOfBirth;

	@Length(max = 50)
	private String heroName;

	private String webscraperOrder;

	private int intelligence;

	private int strength;

	private int speed;

	private int durability;

	private int power;

	private int combat;

	private String alterEgos;

	private String aliases;

	private String firstAppearance;

	private String publisher;

	private String alignment;

	private String gender;

	private String race;

	private String height;

	private String weight;

	private String eyes;

	private String hairs;

	private String occupation;

	private String base;

	private String relatives;

	private String history;

	private String powers;

	private String equipments;

	private String weapons;

//	private LocalDate creationDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "hero_teams",
			joinColumns = @JoinColumn(name = "id_hero", referencedColumnName = "idHero"),
			inverseJoinColumns = @JoinColumn(name = "id_team", referencedColumnName = "idTeam"))
	private List<Team> teams;

	public double getBaseDamages(){
		return (combat+strength+speed+intelligence)/ 4.0 / 10.0;
	}

	@PrePersist
	public void prePersist() {
		//Light form version for formation
		setWebscraperOrder(this.heroName);
		if (StringUtils.isEmpty(this.fullName)) {
			setFullName(this.heroName);
		}
        setAlterEgos("alterEgos");
        setPlaceOfBirth("placeOfBirth");
        setAlignment("good");
        setGender("Female");
        setHeight("height");
        setWeight("weight");
        setEyes("eyes");
        setOccupation("occupation");
        setHistory("Mocked created Hero");
	}

}
