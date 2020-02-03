import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shopping-liste',
  templateUrl: './shopping-liste.component.html',
  styleUrls: ['./shopping-liste.component.css']
})
export class ShoppingListeComponent implements OnInit {

  public listeAchats : string[] = ["fraise tagada", "nouvelle poignee porte", "coca"];
  public listeAchatsFiltree : string[];
  public nouvelAchat: string = "";
  public filtre = "";

  constructor() {
    // copie de la liste
    this.listeAchatsFiltree = this.listeAchats.slice();
   }

  ngOnInit() {
  }

  public ajouterAchat() {
    this.listeAchats.push(this.nouvelAchat);
    this.filtrerListe(this.filtre);
  }

  public filtrerListe(evt: string) {
    this.filtre = evt;
    if (evt == "")
      this.listeAchatsFiltree = this.listeAchats.slice();
    else
      this.listeAchatsFiltree = this.listeAchats.filter(a => a.indexOf(evt) != -1);
  }

}
