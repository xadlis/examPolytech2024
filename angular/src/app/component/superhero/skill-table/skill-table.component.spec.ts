/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SkillBarComponent } from './../skill-bar/skill-bar.component';
import { SkillTableComponent } from './skill-table.component';

describe('SkillTableComponent', () => {
  let component: SkillTableComponent;
  let fixture: ComponentFixture<SkillTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkillTableComponent, SkillBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkillTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
