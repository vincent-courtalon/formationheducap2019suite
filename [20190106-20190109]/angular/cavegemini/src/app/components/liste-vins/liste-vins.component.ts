import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Vin } from 'src/app/metier/vin';

@Component({
  selector: 'app-liste-vins',
  templateUrl: './liste-vins.component.html',
  styleUrls: ['./liste-vins.component.css']
})
export class ListeVinsComponent implements OnInit {

  // cet propriété est une entrée du composant
  // c.a.d que le composant parent peut transmettre des données
  // a notre composant via cette propriété
  @Input("liste_vins") vins : Vin[];
  @Output() onEditVin = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  edit(vin : Vin) : boolean {
    //console.log(vin);
    this.onEditVin.emit(vin);
    return false;
  }

}
