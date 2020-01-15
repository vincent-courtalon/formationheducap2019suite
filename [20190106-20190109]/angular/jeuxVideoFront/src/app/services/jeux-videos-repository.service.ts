import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { JeuxVideo } from '../metier/jeux-video';
import { Page } from '../metier/page';
import { Editeur } from '../metier/editeur';

@Injectable({
  providedIn: 'root'
})
export class JeuxVideosRepositoryService {

  private serviceUrl : string = 'http://localhost:8181/jeuxvideos';

  private jeuxVideosSubject : BehaviorSubject<Page<JeuxVideo>>;

  private numeroPage : number;
  private filtreEditeur : number;

  setNumeroPage(numeroPage : number) : void {
    this.numeroPage = numeroPage;
    this.refreshJeuxVideos();
  }

  setFiltreEditeur(editeurId : number) : void {
    this.filtreEditeur = editeurId;
    this.refreshJeuxVideos();
  }


  constructor(private http : HttpClient) {
    this.jeuxVideosSubject = new BehaviorSubject(Page.emptyPage());
    this.numeroPage = 0;
    this.filtreEditeur = 0;
   }

  getJeuxVideosAsObservable() : Observable<Page<JeuxVideo>> {
    return this.jeuxVideosSubject.asObservable();
  }

  refreshJeuxVideos() : void {
    let urlParams = new HttpParams().set('page' , '' + this.numeroPage)
                                    .set('size', '4');
    if (this.filtreEditeur > 0) {
      urlParams = urlParams.set('editeurId', '' + this.filtreEditeur);
    }
    this.http.get<Page<JeuxVideo>>(this.serviceUrl, {params : urlParams})
            .subscribe(p => {
              this.jeuxVideosSubject.next(p);
              this.numeroPage = p.number;
            });
  }

  getListeEditeurs() : Observable<Editeur[]> {
    return this.http.get<Editeur[]>(`${this.serviceUrl}/editeurs`); 
  }
}
