import { Component, OnInit, OnDestroy } from '@angular/core';
import { AnimalRepositoryService } from 'src/app/services/animal-repository.service';
import { Animal } from 'src/app/metier/Animal';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-animaux-liste',
  templateUrl: './animaux-liste.component.html',
  styleUrls: ['./animaux-liste.component.css']
})
export class AnimauxListeComponent implements OnInit, OnDestroy {


  public animaux: Animal[];
  // pagination
  public pageNo : number;
  public pageSize: number;
  public totalElements : number;
  // abonnement
  private animauxSubscription : Subscription;

  constructor(private animalRepository: AnimalRepositoryService) { }
  

  ngOnInit() {
    this.animauxSubscription = this.animalRepository.getAnimauxAsObservable()
                                    .subscribe( p => {
                                      this.animaux = p.content;
                                      this.pageNo = p.number + 1;
                                      this.pageSize = p.size;
                                      this.totalElements = p.totalElements;
                                    });
    this.animalRepository.refreshAnimaux();
  }

  public onPageChanged(event) {
    this.animalRepository.setPageNo(event.page - 1);
    this.animalRepository.refreshAnimaux();
  }

  ngOnDestroy(): void {
    this.animauxSubscription.unsubscribe();
  }

}
