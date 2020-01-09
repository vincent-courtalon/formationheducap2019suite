import { Component, OnInit, OnDestroy } from '@angular/core';
import { VinRepositoryService } from 'src/app/services/vin-repository.service';
import { Vin } from 'src/app/metier/vin';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { Terroir } from 'src/app/metier/terroir';

@Component({
  selector: 'app-affichage-vins',
  templateUrl: './affichage-vins.component.html',
  styleUrls: ['./affichage-vins.component.css']
})
export class AffichageVinsComponent implements OnInit, OnDestroy {
 

  constructor(private vinRepository : VinRepositoryService,
              private router : Router) { }

  public vins : Vin[];
  public terroirs : Terroir[];
  private vinsSouscription : Subscription;

  ngOnInit() {
    this.vinsSouscription = this.vinRepository.getVinsAsObservable()
                                .subscribe( newvins => this.vins = newvins);
    this.vinRepository.refreshListe();
    // récupération de la liste des terroirs
    this.vinRepository.listeTerroirs().subscribe(lst => this.terroirs = lst);
  }

  ngOnDestroy(): void {
    this.vinsSouscription.unsubscribe();
  }

  edition(event) : void {
    this.router.navigateByUrl('/edit/' + event.id);
    //console.log(event);
  }

  changeFiltreTerroir(event) : void {
    this.vinRepository.setFiltreTerroirId(event);
  }
}
