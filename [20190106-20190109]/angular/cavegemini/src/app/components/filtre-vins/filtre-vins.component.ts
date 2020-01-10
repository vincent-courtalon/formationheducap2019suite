import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Terroir } from 'src/app/metier/terroir';
import { Caracteristique } from 'src/app/metier/caracteristique';

@Component({
  selector: 'app-filtre-vins',
  templateUrl: './filtre-vins.component.html',
  styleUrls: ['./filtre-vins.component.css']
})
export class FiltreVinsComponent implements OnInit {
  @Input() terroirs : Terroir[];
  @Output() onTerroirIdClick  = new EventEmitter<number>();

  @Input() 
  set caracteristiques(car :Caracteristique[]) {
    // methode est appelée quand des données arrive via notre input
    if (car) {
      this.lesCaracteristiques = car.map(cr => [cr, false]);
    }
  }
  @Output() selectedCaracteristiques = new EventEmitter<number[]>();

  choixTerroir : number;
  // tableau de tuples
  lesCaracteristiques : [Caracteristique, boolean][];

  constructor() { }

  ngOnInit() {
    this.choixTerroir = 0;
  }

  onChoixTerroir(event : number) {
    //console.log(event);
    this.onTerroirIdClick.emit(event);
  }

  switchCarac(carac : [Caracteristique, boolean]) {
    carac[1] = !carac[1];
    this.sendSelectedIds();
  }

  sendSelectedIds() : void {
    let selectedids = this.lesCaracteristiques
                          .filter(cr => cr[1])
                          .map(cr => cr[0].id);
    console.log(selectedids);
    this.selectedCaracteristiques.emit(selectedids);
  }
}
