import { HeroService } from './../../../core/hero.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Component, OnInit, Inject, Optional } from '@angular/core';
import { Superhero } from './../../../models/superhero';

@Component({

  selector: 'app-superhero-form',
  // templateUrl: './superhero-form.component.html',
  templateUrl: './superhero-form-material.component.html',
  styleUrls: ['./superhero-form.component.css']
})
export class SuperheroFormComponent implements OnInit {
  readonly heroFormFields = [
    { name: 'heroName', label: 'Hero name', type: 'text', placeholder: 'Iron Man' },
    { name: 'fullName', label: 'Full hero name', type: 'text', placeholder: 'Anthony Edward Stark' },
    { name: 'intelligence', label: 'Intelligence', type: 'number' },
    { name: 'strength', label: 'Strength', type: 'number' },
    { name: 'speed', label: 'Speed', type: 'number' },
    { name: 'durability', label: 'Durability', type: 'number' },
    { name: 'power', label: 'Power', type: 'number' },
    { name: 'combat', label: 'Combat', type: 'number' },
  ];

  heroForm: FormGroup;
  initialized = false;


  constructor(
    public dialogRef: MatDialogRef<SuperheroFormComponent>,
    private _heroService: HeroService,
    private _formBuilder: FormBuilder,
    @Optional() @Inject(MAT_DIALOG_DATA) public superhero: Superhero
  ) {
    this.buildFormGroup();
  }

  ngOnInit() {
  }

  getPlaceholder(formField): string {
    return formField.placeholder || '';
  }

  handleForm(): void {
    const heroInformation = { ...this.superhero, ...this.heroForm.value };
    if (heroInformation.idHero) {
      this.updateHero(heroInformation);
    } else {
      this.createHero(heroInformation);
    }
  }

  updateHero(heroInformation: Superhero): void {
    this._heroService.updateHeroe(this.superhero.idHero, heroInformation).subscribe(() => {
      this.dialogRef.close(heroInformation);
    }, (error) => {
      console.log(error);
    });
  }

  createHero(heroInformation: Superhero): void {
    this._heroService.addHeroe(heroInformation).subscribe(() => {
      this.dialogRef.close(heroInformation);
    }, (error) => {
      console.log(error);
    });
  }

  cancel(): void {
    this.dialogRef.close();
  }

  private buildFormGroup(): void {
    const formGroup = {};
    this.heroFormFields.forEach(formField => {
      if (this.superhero) {
        formGroup[formField.name] = this._formBuilder.control(this.superhero[formField.name]);
      } else {
        formGroup[formField.name] = this._formBuilder.control(formField.type === 'text' ? '' : 0);
      }
    });
    this.heroForm = this._formBuilder.group(formGroup);
  }
}
