import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VinRepositoryService } from 'src/app/services/vin-repository.service';
import { Vin } from 'src/app/metier/vin';
import { Terroir } from 'src/app/metier/terroir';

@Component({
  selector: 'app-edit-vin',
  templateUrl: './edit-vin.component.html',
  styleUrls: ['./edit-vin.component.css']
})
export class EditVinComponent implements OnInit {

  editedVin : Vin;
  listeterroirs : Terroir[];
  idTerroir : number;

  constructor(private router : Router,
              private activeRoute: ActivatedRoute,
              private vinRepository : VinRepositoryService) { }

  ngOnInit() {
    this.editedVin = null;
    this.listeterroirs = null;
    this.idTerroir = 0;
    this.activeRoute.params.subscribe(
      params =>  {
        console.log("id = " + params["id"]);
        this.vinRepository.findVinById(params["id"])
            .subscribe( vin =>  {
              this.editedVin = vin;
              // au cas ou le vin n'a pas de terroir -> idTerroir = 0
              this.idTerroir = (this.editedVin.terroir ? this.editedVin.terroir.id : 0);
            }, 
                        error => this.router.navigateByUrl("/"));
        this.vinRepository.listeTerroirs()
            .subscribe( terroirs => this.listeterroirs = terroirs);
      });
  }

  update() : boolean {
    this.vinRepository.updateVin(this.editedVin, this.idTerroir)
        .toPromise().then(v => {
          console.log (v);
          this.router.navigateByUrl("/");
        });
    return false;
  }

  
}
