/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MaterialModule } from './../../../shared/material.module';
import { MenubarModule } from './../../../shared/menubar/menubar.module';
import { HeroService } from './../../../core/hero.service';
import { ImageService } from './../../../core/image.service';
import { FightService } from './../../../core/fight.service';
import { SuperheroListComponent } from './superhero-list.component';
import { FilterPipe } from './../../../shared/filter/filter.pipe';


describe('SuperheroListComponent', () => {
  let component: SuperheroListComponent;
  let fixture: ComponentFixture<SuperheroListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, MenubarModule, MaterialModule],
      declarations: [ SuperheroListComponent, FilterPipe ],
      providers: [ HeroService, ImageService, FightService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuperheroListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
