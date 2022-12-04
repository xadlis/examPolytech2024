DROP TABLE IF EXISTS hero;
CREATE TABLE hero (
  id_hero int(11) NOT NULL AUTO_INCREMENT,
  webscraper_order varchar(20) NOT NULL,
  hero_name varchar(50) NOT NULL,
  intelligence decimal(10,0) DEFAULT NULL,
  strength decimal(10,0) DEFAULT NULL,
  speed decimal(10,0) DEFAULT NULL,
  durability decimal(10,0) DEFAULT NULL,
  power decimal(10,0) DEFAULT NULL,
  combat decimal(10,0) DEFAULT NULL,
  full_name varchar(100) NOT NULL,
  alter_egos varchar(50) NOT NULL,
  aliases text,
  place_of_birth varchar(100) NOT NULL,
  first_appearance text,
  publisher varchar(20) DEFAULT NULL,
  alignment varchar(10) NOT NULL,
  gender varchar(10) NOT NULL,
  race varchar(20) DEFAULT NULL,
  height varchar(30) NOT NULL,
  weight varchar(40) NOT NULL,
  eyes varchar(30) NOT NULL,
  hairs varchar(20) DEFAULT NULL,
  occupation text NOT NULL,
  base text,
  relatives text,
  history text,
  powers text,
  equipments text,
  weapons text,
  PRIMARY KEY (id_hero)
) ;

DROP TABLE IF EXISTS hero_teams;
CREATE TABLE hero_teams (
  id_hero int(11) NOT NULL,
  id_team int(11) NOT NULL,
  PRIMARY KEY (id_hero,id_team)
);

DROP TABLE IF EXISTS teams;
CREATE TABLE teams (
  id_team int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id_team)
);