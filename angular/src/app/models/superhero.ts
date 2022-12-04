import { Member } from './member';

export interface Superhero {
  idHero: number;
  fullName: string;
  placeOfBirth: string;
  imageSrc: string;
  heroName: string;
  webscraperOrder?: string;
  intelligence: number;
  strength: number;
  speed: number;
  durability: number;
  power: number;
  combat: number;
  alterEgos: string;
  aliases: string;
  firstAppearance: string;
  publisher: string;
  alignment: string;
  gender: string;
  race: string;
  height: string;
  weight: string;
  eyes: string;
  hairs: string;
  occupation: string;
  base: string;
  relatives: string;
  history: string;
  powers: string;
  equipments: string;
  weapons: string;
  teams: Member[];
}
