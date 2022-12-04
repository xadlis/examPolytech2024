import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { AuthenticationService } from '../../core/authentication.service';
import { Router } from '@angular/router';

@Injectable()
export class AddHeaderInterceptor implements HttpInterceptor {

  constructor(private authService: AuthenticationService,
              private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // Clone the request to add the new header
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser) {
            const newHeaders = { headers: req.headers.set('Authorization', `${currentUser.token}`) };
            const clonedRequest = req.clone(newHeaders);
            // Pass the cloned request instead of the original request to the next handle
            return next.handle(clonedRequest).pipe(
              tap((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    console.log('event--->>>', event);
                }
              }, (err: any) => {
                if (err instanceof HttpErrorResponse && err.status === 403) {
                  this.authService.logout();
                    this.router.navigate(['/login']);
                }
              })
            );
        } else {
            const clonedRequest = req.clone();
            return next.handle(clonedRequest);
        }
    }
}
