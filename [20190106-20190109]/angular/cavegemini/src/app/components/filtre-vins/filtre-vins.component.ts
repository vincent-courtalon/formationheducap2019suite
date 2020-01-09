import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Terroir } from 'src/app/metier/terroir';

@Component({
  selector: 'app-filtre-vins',
  templateUrl: './filtre-vins.component.html',
  styleUrls: ['./filtre-vins.component.css']
})
export class FiltreVinsComponent implements OnInit {
  @Input() terroirs : Terroir[];
  @Output() onTerroirIdClick  = new EventEmitter<number>();

  choixTerroir : number;

  constructor() { }

  ngOnInit() {
    this.choixTerroir = 0;
  }

  onChoixTerroir(event : number) {
    //console.log(event);
    this.onTerroirIdClick.emit(event);
  }
}
