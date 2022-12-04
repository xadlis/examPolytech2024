import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.css']
})
export class MenubarComponent  {
  @Output() create: EventEmitter<any> = new EventEmitter<any>();

  public searchText = '';
  @Output() change: EventEmitter<string> = new EventEmitter<string>();

  constructor() { }

  add(): void {
    this.create.emit(null);
  }

  refreshFilter() {
    this.change.emit(this.searchText);
  }

}