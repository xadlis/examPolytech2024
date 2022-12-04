import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Superhero } from '../models/superhero';
import { environment } from './../../environments/environment';

@Injectable()
export class HeroService {


  constructor(private _http: HttpClient) {}

  getHeroes(): Observable<Superhero[]> {
    return this._http.get<Superhero[]>(`${environment.backendUrl}/superheroes`);
  }

  getHeroe(id: number): Observable<Superhero> {
    return this._http.get<Superhero>(`${environment.backendUrl}/superheroes/${id}`);
  }

  updateHeroe(id: number, data: any): Observable<Object> {
    return this._http.put(`${environment.backendUrl}/superheroes/${id}`, data);
  }

  addHeroe(data: any): Observable<Object> {
    return this._http.post(`${environment.backendUrl}/superheroes`, data);
  }

}
