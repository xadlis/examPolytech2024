import { Member } from './member';
import { FightStep } from './fightStep';
export interface FightResponse {
  fighters: Member[];
  steps: FightStep[];
  winner: Member;
}
