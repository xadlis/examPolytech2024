import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FightResponse } from '../models/fightResponse';
import { environment } from './../../environments/environment';

@Injectable()
export class FightService {


  constructor(private _http: HttpClient) {}

  duel(idHero1: number, idHero2: number): Observable<FightResponse> {
    return this._http.get<FightResponse>(`${environment.backendUrl}/fights/${idHero1}/vs/${idHero2}`);
  }

}
