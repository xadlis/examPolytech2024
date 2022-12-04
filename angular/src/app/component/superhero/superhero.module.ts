import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SuperheroListComponent } from './superhero-list/superhero-list.component';
import { SuperheroDetailComponent } from './superhero-detail/superhero-detail.component';
import { MaterialModule } from './../../shared/material.module';
import { SkillTableComponent } from './skill-table/skill-table.component';
import { SkillBarComponent } from './skill-bar/skill-bar.component';
import { SuperheroFormComponent } from './superhero-form/superhero-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  declarations: [
    SuperheroListComponent,
    SuperheroDetailComponent,
    SkillTableComponent,
    SkillBarComponent,
    SuperheroFormComponent
  ],
  entryComponents : [
    SuperheroDetailComponent,
    SuperheroFormComponent
  ],
  exports : [
    SuperheroListComponent
  ],
})
export class SuperheroModule { }
