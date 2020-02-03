import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-compteur-affiche',
  templateUrl: './compteur-affiche.component.html',
  styleUrls: ['./compteur-affiche.component.css']
})
export class CompteurAfficheComponent implements OnInit {

  public title ='compteur app';
  public message : string = 'coucou';
  public compteur : number = 1;

  constructor() { }

  ngOnInit() {
  }

  public incrementeCompteur() {
    this.compteur++;
  }
}
