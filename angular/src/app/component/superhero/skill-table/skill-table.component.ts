import { Component, OnInit, Input } from '@angular/core';

import { Superhero } from './../../../models/superhero';

@Component({
  selector: 'app-skill-table',
  templateUrl: './skill-table.component.html',
  styleUrls: ['./skill-table.component.css']
})
export class SkillTableComponent implements OnInit {

  @Input() public superhero: Superhero;

  skills = ['intelligence', 'strength', 'speed', 'durability', 'power', 'combat'];

  constructor() { }

  ngOnInit() {
  }

}
