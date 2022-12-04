
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { FilterPipe } from './filter/filter.pipe';
import { MenubarModule } from './menubar/menubar.module';
import {HttpClientModule} from '@angular/common/http';
@NgModule({
  imports: [
    // Angular
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ],
  declarations: [
    FilterPipe
  ],
  exports: [
    // Angular
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    // Internal
    FilterPipe,
    MenubarModule
  ],
  providers: [
    DatePipe,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [],
})
export class SharedModule { }
