import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';
import { SuperheroListComponent } from './component/superhero/superhero-list/superhero-list.component';
import { LoginComponent } from './component/login/login.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: SuperheroListComponent, canActivate: [AuthGuard] },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const appRoutes = RouterModule.forRoot(routes);

