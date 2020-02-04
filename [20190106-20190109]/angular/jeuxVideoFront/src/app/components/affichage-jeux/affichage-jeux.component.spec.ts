import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffichageJeuxComponent } from './affichage-jeux.component';
import { MockComponent } from "ng-mocks";
import { ListeJeuxComponent } from '../liste-jeux/liste-jeux.component';
import { FiltreJeuxComponent } from '../filtre-jeux/filtre-jeux.component';
import { Observable } from 'rxjs';
import { Page } from 'src/app/metier/page';
import { JeuxVideo } from 'src/app/metier/jeux-video';
import { Editeur } from 'src/app/metier/editeur';
import { JeuxVideosRepositoryService } from 'src/app/services/jeux-videos-repository.service';

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
  let spyJeuxVideosRepository;
  beforeEach(async(() => {
    spyOn(fakeJeuxVideosRepository, 'refreshJeuxVideos');
    spyOn(fakeJeuxVideosRepository, 'getJeuxVideosAsObservable');
    spyJeuxVideosRepository = spyOn(fakeJeuxVideosRepository, 'getListeEditeurs');

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
});
