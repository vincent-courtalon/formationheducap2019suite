import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Editeur } from 'src/app/metier/editeur';

@Component({
  selector: 'app-filtre-jeux',
  templateUrl: './filtre-jeux.component.html',
  styleUrls: ['./filtre-jeux.component.css']
})
export class FiltreJeuxComponent implements OnInit {


  @Input() editeurs : Editeur[];
  @Output() filtreEditeur  = new EventEmitter<number>();

  choixEditeur : number = 0;

  constructor() {
    this.editeurs = [];
   }

  ngOnInit() {
  }

  onChoixEditeur(event : number) : void {
    console.log("choix: " + event);
    this.filtreEditeur.emit(event); // envoie de l'id de l'editeur choisi (pour filtrage)
  }

}
