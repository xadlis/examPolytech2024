import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenubarComponent } from './menubar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [MenubarComponent],
  exports : [
    MenubarComponent
  ]
})
export class MenubarModule { }