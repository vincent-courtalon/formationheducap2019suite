import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffichageJeuxComponent } from './affichage-jeux.component';
import { MockComponent } from "ng-mocks";
import { ListeJeuxComponent } from '../liste-jeux/liste-jeux.component';
import { FiltreJeuxComponent } from '../filtre-jeux/filtre-jeux.component';
import { Observable, of } from 'rxjs';
import { Page } from 'src/app/metier/page';
import { JeuxVideo } from 'src/app/metier/jeux-video';
import { Editeur } from 'src/app/metier/editeur';
import { JeuxVideosRepositoryService } from 'src/app/services/jeux-videos-repository.service';
import { By } from '@angular/platform-browser';

class FakeJeuxVideosRepository {
  public getJeuxVideosAsObservable() : Observable<Page<JeuxVideo>> {
    return null;
  }

  public refreshJeuxVideos() : void {
  }

  public getListeEditeurs() : Observable<Editeur[]> {
    return null;
  }
}

describe('AffichageJeuxComponent', () => {
  const fakeJeuxVideosRepository = new FakeJeuxVideosRepository();
  //let spyJeuxVideosRepository;
  beforeEach(async(() => {
    spyOn(fakeJeuxVideosRepository, 'refreshJeuxVideos');
    spyOn(fakeJeuxVideosRepository, 'getJeuxVideosAsObservable').and.returnValue(of(
      new Page<JeuxVideo>(
        [new JeuxVideo(1, "adibou et la physique quantique", new Date(),
                          new Editeur(1, "adibou", "adibou@gmail.com")
                          ,null, null),
        new JeuxVideo(1, "maestro et le corona virus pas sympa", new Date(),
                          new Editeur(1, "maestro", "maestro@gmail.com"),
                          null, null)
        ],
        2, 0, 10, 1, 2, true, true, false)
    ));
    spyOn(fakeJeuxVideosRepository, 'getListeEditeurs')
                        .and.returnValue(
                          of([new Editeur(1, "adibou", "adibou@educ.com", null),
                            new Editeur(2, "maestro", "maestro@editeur.com", null)]));

    TestBed.configureTestingModule({
      declarations: [ AffichageJeuxComponent,
                     MockComponent(ListeJeuxComponent),
                      MockComponent(FiltreJeuxComponent) ],
      providers : [ 
        {provide : JeuxVideosRepositoryService, useValue: fakeJeuxVideosRepository }
      ]

    })
    .compileComponents();
  }));


  it('should create', () => {
    const fixture = TestBed.createComponent(AffichageJeuxComponent);
    const component = fixture.debugElement.componentInstance;
    expect(component).toBeTruthy();

  });

  it('should transmit editors and jeux videos page to child components', () => {
    const fixture = TestBed.createComponent(AffichageJeuxComponent);
    const component = fixture.debugElement.componentInstance;
    const listeJeuxComponent = fixture.debugElement.query(By.css('app-liste-jeux')).componentInstance as ListeJeuxComponent;
    const filtreJeuxComponent = fixture.debugElement.query(By.css('app-filtre-jeux')).componentInstance as FiltreJeuxComponent;

    // les données proviennent de notre fake repository, via les spyOn
    
    fixture.detectChanges();
    // test propagation des donnée au composant listeJeux
    expect(listeJeuxComponent.jeuxvideos.numberOfElements).toEqual(2);
    expect(listeJeuxComponent.jeuxvideos.content[0].nom).toEqual("adibou et la physique quantique");
    // test propagation des données au composant filtreJeux
    expect(filtreJeuxComponent.editeurs.length).toEqual(2);
    expect(filtreJeuxComponent.editeurs[0].email).toEqual("adibou@educ.com");

  });
});
