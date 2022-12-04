import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './../../core/authentication.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model: any = {};
    error: string;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService) { }

    ngOnInit() {
        // reset login status
        this.authenticationService.logout();
    }

    login() {
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe((result: boolean) => {
                if (result) {
                    this.router.navigate(['/']);
                } else {
                    this.error = 'Bad credentials';
                }
            },
                (error) => {
                    this.error = 'Unexpected Error';
                });
    }
}

