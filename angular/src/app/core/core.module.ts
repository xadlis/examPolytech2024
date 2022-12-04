import { NgModule } from '@angular/core';
import { HeroService } from './hero.service';
import { ImageService } from './image.service';
import { FightService } from './fight.service';
import { AuthenticationService } from './authentication.service';
@NgModule({
  imports: [],
  declarations: [],
  exports: [],
  providers: [
    HeroService,
    ImageService,
    FightService,
    AuthenticationService
  ]
})
export class CoreModule { }
