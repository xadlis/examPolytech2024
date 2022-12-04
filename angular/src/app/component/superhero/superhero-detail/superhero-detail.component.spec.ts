/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { SkillTableComponent } from './../skill-table/skill-table.component';
import { SkillBarComponent } from './../skill-bar/skill-bar.component';
import { SuperheroDetailComponent } from './superhero-detail.component';
import { ImageService } from './../../../core/image.service';

describe('SuperheroDetailComponent', () => {
  let component: SuperheroDetailComponent;
  let fixture: ComponentFixture<SuperheroDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MatDialogModule, ReactiveFormsModule, FormsModule],
      declarations: [ SuperheroDetailComponent, SkillTableComponent, SkillBarComponent],
      providers: [
        // workaround: why I can't inject MatDialogRef in the unit test?
        { provide: MatDialogRef, useValue: {} },
        { provide: MAT_DIALOG_DATA, useValue: {} },
        ImageService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperheroDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
