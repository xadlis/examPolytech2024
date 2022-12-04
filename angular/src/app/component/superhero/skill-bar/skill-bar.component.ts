import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-skill-bar',
  templateUrl: './skill-bar.component.html',
  styleUrls: ['./skill-bar.component.css']
})
export class SkillBarComponent implements OnInit {

  @Input() public value: number;

  constructor() { }

  ngOnInit() {
  }

}
