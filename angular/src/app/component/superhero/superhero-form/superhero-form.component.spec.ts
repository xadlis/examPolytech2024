/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';


import { MaterialModule } from './../../../shared/material.module';
import { SuperheroFormComponent } from './superhero-form.component';
import { HeroService } from './../../../core/hero.service';

describe('SuperheroFormComponent', () => {
  let component: SuperheroFormComponent;
  let fixture: ComponentFixture<SuperheroFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ FormsModule, ReactiveFormsModule, HttpClientTestingModule, NoopAnimationsModule, MaterialModule],
      declarations: [ SuperheroFormComponent ],
      providers: [
        // workaround: why I can't inject MatDialogRef in the unit test?
        { provide: MatDialogRef, useValue: {} },
        HeroService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperheroFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
