import { Component, OnInit, OnDestroy } from '@angular/core';
import { VinRepositoryService } from 'src/app/services/vin-repository.service';
import { Vin } from 'src/app/metier/vin';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-affichage-vins',
  templateUrl: './affichage-vins.component.html',
  styleUrls: ['./affichage-vins.component.css']
})
export class AffichageVinsComponent implements OnInit, OnDestroy {
 

  constructor(private vinRepository : VinRepositoryService,
              private router : Router) { }

  public vins : Vin[];
  private vinsSouscription : Subscription;

  ngOnInit() {
    this.vinsSouscription = this.vinRepository.getVinsAsObservable()
                                .subscribe( newvins => this.vins = newvins);
    this.vinRepository.refreshListe();
  }

  ngOnDestroy(): void {
    this.vinsSouscription.unsubscribe();
  }

  edition(event) : void {
    this.router.navigateByUrl('/edit/' + event.id);
    //console.log(event);
  }
}
