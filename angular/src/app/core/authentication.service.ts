import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from './../../environments/environment';

@Injectable()
export class AuthenticationService {
    public token: string;

    constructor(private _http: HttpClient) {
        // set token if saved in local storage
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }

    login(username: string, password: string): EventEmitter<boolean> {
        const result: EventEmitter<boolean> = new EventEmitter<boolean>();
        this._http.post(`${environment.backendUrl}/authenticate`, { login: username, password: password }, { responseType: 'text' })
            .subscribe((response: string) => {
                // login successful if there's a jwt token in the response
                if (response) {
                    // set token property
                    this.token = response;
                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ username: username, token: this.token }));
                    // return true to indicate successful login
                    result.emit(true);
                } else {
                    // return false to indicate failed login
                    result.emit(false);
                }
            }, (error) => {
                result.emit(false);
            });
        return result;
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}
