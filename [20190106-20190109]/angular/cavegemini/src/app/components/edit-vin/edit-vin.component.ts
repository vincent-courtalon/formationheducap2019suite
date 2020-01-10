import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VinRepositoryService } from 'src/app/services/vin-repository.service';
import { Vin } from 'src/app/metier/vin';
import { Terroir } from 'src/app/metier/terroir';
import { CaracteristiqueRepositoryService } from 'src/app/services/caracteristique-repository.service';
import { Caracteristique } from 'src/app/metier/caracteristique';

@Component({
  selector: 'app-edit-vin',
  templateUrl: './edit-vin.component.html',
  styleUrls: ['./edit-vin.component.css']
})
export class EditVinComponent implements OnInit {

  editedVin : Vin;
  listeterroirs : Terroir[];
  idTerroir : number;
  listeCaracteristiques : Caracteristique[];

  listeCaracVin : Caracteristique[]; // les caracteristiques du vin en edition
  listeNotCaracVin : Caracteristique[]; // les autres caracteristiques

  constructor(private router : Router,
              private activeRoute: ActivatedRoute,
              private vinRepository : VinRepositoryService,
              private caracteristiqueRepository : CaracteristiqueRepositoryService) { }

  ngOnInit() {
    this.editedVin = null;
    this.listeterroirs = null;
    this.listeCaracteristiques = null;

    this.idTerroir = 0;
    this.activeRoute.params.subscribe(
      params =>  {
        console.log("id = " + params["id"]);
        this.vinRepository.findVinById(params["id"])
            .subscribe( vin =>  {
              this.editedVin = vin;
              this.initCaracteristiques();
              // au cas ou le vin n'a pas de terroir -> idTerroir = 0
              this.idTerroir = (this.editedVin.terroir ? this.editedVin.terroir.id : 0);
            }, 
                        error => this.router.navigateByUrl("/"));
        this.vinRepository.listeTerroirs()
            .subscribe( terroirs => this.listeterroirs = terroirs);
        this.caracteristiqueRepository.getListeCaracteristiques()
            .subscribe( caracs => {
              this.listeCaracteristiques = caracs;
              this.initCaracteristiques();
            });
      });
  }

  initCaracteristiques() : void {
    // il nous faut les deux données pour initialiser les caracteristiques
    if (this.listeCaracteristiques && this.editedVin) {
      this.listeCaracVin = this.editedVin.caracteristiques.slice();
      this.listeNotCaracVin = [];
      // je parcours toutes les caractertiques
      this.listeCaracteristiques.forEach(cglobale => {
        // si la caracteristiques n'est pas trouvée dans les caracteristiques du vin
        if (!(this.listeCaracVin.find(cvin => cglobale.id == cvin.id))) {
          // je l'ajoute au tableau des caracteristiques non associées au vin
          this.listeNotCaracVin.push(cglobale);
        }
      });
    }
  }

  ajouterCaracteristiques(cr : Caracteristique) : void {
    console.log("ajouter " + cr.libelle);
    let index = this.listeNotCaracVin.indexOf(cr);
    this.listeCaracVin.push(this.listeNotCaracVin.splice(index, 1)[0]);
  }
  retirerCaracteristiques(cr : Caracteristique) : void {
    console.log("retirer " + cr.libelle);
    let index = this.listeCaracVin.indexOf(cr);
    this.listeNotCaracVin.push(this.listeCaracVin.splice(index, 1)[0]);
  }
  

  update() : boolean {
    this.vinRepository.updateVin( this.editedVin, 
                                  this.idTerroir,
                                  this.listeCaracVin.map(cr => cr.id))
        .toPromise().then(v => {
          console.log (v);
          this.router.navigateByUrl("/");
        });
    return false;
  }

  
}
