import { Component, OnInit, Inject, ChangeDetectorRef } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SafeStyle } from '@angular/platform-browser';

import { Superhero } from './../../../models/superhero';
import { SuperheroFormComponent } from '../superhero-form/superhero-form.component';
import { ImageService } from './../../../core/image.service';

@Component({
  selector: 'app-superhero-detail',
  templateUrl: './superhero-detail.component.html',
  styleUrls: ['./superhero-detail.component.css']
})
export class SuperheroDetailComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<SuperheroDetailComponent>,
    private _imageService: ImageService,
    private _dialog: MatDialog,
    private _ref: ChangeDetectorRef,
    @Inject(MAT_DIALOG_DATA) public superhero: Superhero) {}

  ngOnInit() {
  }

  getAvatar(source: string): SafeStyle {
    return this._imageService.getImageUrl(source);
  }

  openUpdateModal(): void {
    // open update modal
    const dialogRef = this._dialog.open(SuperheroFormComponent, {
      width: '20%',
      data: this.superhero
    });

    dialogRef.beforeClose().subscribe(result => {
      this.superhero = result;
    });
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
