import { Component, OnInit, OnDestroy } from '@angular/core';
import { JeuxVideosRepositoryService } from 'src/app/services/jeux-videos-repository.service';
import { Page } from 'src/app/metier/page';
import { JeuxVideo } from 'src/app/metier/jeux-video';
import { Subscription } from 'rxjs';
import { Editeur } from 'src/app/metier/editeur';

@Component({
  selector: 'app-affichage-jeux',
  templateUrl: './affichage-jeux.component.html',
  styleUrls: ['./affichage-jeux.component.css']
})
export class AffichageJeuxComponent implements OnInit, OnDestroy {


  constructor(private jeuxVideosRepository : JeuxVideosRepositoryService) { }

  public jeuxVideos : Page<JeuxVideo> = Page.emptyPage();
  private jeuxVideosSouscription : Subscription;
  public editeurs : Editeur[] = [];


  ngOnInit() {
    this.jeuxVideosSouscription = this.jeuxVideosRepository.getJeuxVideosAsObservable()
                                      .subscribe(p => this.jeuxVideos = p);
    this.jeuxVideosRepository.refreshJeuxVideos();
    this.jeuxVideosRepository.getListeEditeurs().subscribe(editeurs => this.editeurs = editeurs);
  }

  ngOnDestroy(): void {
   this.jeuxVideosSouscription.unsubscribe();
  }

  changePage(event : number) : void {
    console.log("change : " + event);
    this.jeuxVideosRepository.setNumeroPage(event);
  }

  changeEditeur(event : number) : void {
    this.jeuxVideosRepository.setFiltreEditeur(event);
  }

}
