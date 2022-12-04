import {MatCardModule, MatGridListModule, MatButtonModule,
  MatFormFieldModule, MatInputModule, MatListModule, MatIconModule} from '@angular/material';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {MatDialogModule} from '@angular/material/dialog';

@NgModule({
  imports: [],
  exports: [MatCardModule, MatGridListModule, MatDialogModule,
    MatButtonModule, MatFormFieldModule, MatInputModule,
    MatListModule, MatIconModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class MaterialModule { }
