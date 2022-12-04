import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';


import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';

import { SuperheroModule } from './component/superhero/superhero.module';
import { LoginModule } from './component/login/login.module';

import { AppComponent } from './app.component';

import { AuthGuard } from './guards/auth.guard';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddHeaderInterceptor } from './shared/interceptors/header.interceptor';
import { appRoutes } from './app.routing';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    appRoutes,
    SharedModule,
    CoreModule,
    SuperheroModule,
    LoginModule
  ],
  providers: [AuthGuard,
    { provide: HTTP_INTERCEPTORS,
      useClass: AddHeaderInterceptor,
      multi: true,
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
