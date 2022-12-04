import { SuperheroFormComponent } from './../superhero-form/superhero-form.component';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { SafeStyle } from '@angular/platform-browser';

import { Superhero } from './../../../models/superhero';
import { SuperheroDetailComponent } from './../superhero-detail/superhero-detail.component';
import { HeroService } from './../../../core/hero.service';
import { ImageService } from './../../../core/image.service';
import { FightService } from './../../../core/fight.service';

@Component({
  selector: 'app-superhero-list',
  templateUrl: './superhero-list.component.html',
  styleUrls: ['./superhero-list.component.css']
})
export class SuperheroListComponent implements OnInit {

    public superheroes: Superhero[] = [];
    public teamA: Superhero[] = [];
    public teamB: Superhero[] = [];
    public fightResult: string;

    private pagination = {
        firstElemIndex : 1,
        currentPage : 0,
        itemsPerPage: 10,
        totalItems: 0
    };

    public searchText: string;

    private subs: any[] = [];

    constructor(
        private _fb: FormBuilder,
        private _heroService: HeroService,
        private _imageService: ImageService,
        private _fightService: FightService,
        private _dialog: MatDialog
    ) {

    }

    ngOnInit() {
        this.getHeroesList();
    }

    getHeroesList(): void {
        this._heroService.getHeroes().subscribe(
            data => this.superheroes = data.sort(
                    (a, b) => {
                        if (a.heroName < b.heroName) {
                            return -1;
                        }
                        if (a.heroName > b.heroName) {
                            return 1;
                        }
                        return 0;
                    }
                ).filter(
                    (hero) => hero.history
                ),
            error => console.log(error)
          );
    }

    getBackground(source: string): SafeStyle {
        return this._imageService.getImageUrl(source);
    }

    selectSuperhero(heroClicked: Superhero): void {
        const dialogRef = this._dialog.open(SuperheroDetailComponent, {
          width: '1700px',
          height: '95%',
          data: heroClicked
        });

        dialogRef.afterClosed().subscribe(result => {
          // refresh list
          this.getHeroesList();
          this.teamA = [];
          this.teamB = [];
        });
    }

    changeTeam(heroClicked: Superhero): void {
        console.log(heroClicked);
        const indexTeamA = this.teamA.indexOf(heroClicked);
        const indexTeamB = this.teamB.indexOf(heroClicked);
        const currentMaxSize = 1;
        if (indexTeamA < 0) {
            if (indexTeamB < 0 ) {
                if (this.teamA.length < currentMaxSize ) {
                     // Not in a list, and TeamA not full
                    this.teamA.push(heroClicked);
                } else {
                    // Team A is full, so go B
                    if (this.teamB.length < currentMaxSize ) {
                        this.teamB.push(heroClicked);
                    }
                    // 2 Teams are full, do nothing
                }
            } else {
                // Item already in B, remove it
                this.teamB.splice(indexTeamB);
            }
        } else {
            // Item already in A, remove it before move it in B
            this.teamA.splice(indexTeamA);
            if (this.teamB.length < currentMaxSize ) {
                // Team B not full, so go B
                this.teamB.push(heroClicked);
            }
            // Team B is already full, do nothing
        }
    }

    openCreateModal(): void {
        // open update modal
        const dialogRef = this._dialog.open(SuperheroFormComponent, {
          width: '30%'
        });

        dialogRef.beforeClose().subscribe(result => {
          console.log('closed');
        });
    }

    handleSearch(searchFromBar: string) {
        this.searchText = searchFromBar;
    }

    fight(): void {
        this._fightService.duel(this.teamA[0].idHero, this.teamB[0].idHero).subscribe(
            data => this.fightResult = `${data.winner.name} wins in ${data.steps.length} steps`,
            error => console.log(error)
        );
    }
}
