import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompteurAfficheComponent } from './compteur-affiche.component';

describe('CompteurAfficheComponent', () => {
  //let component: CompteurAfficheComponent;
  //let fixture: ComponentFixture<CompteurAfficheComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompteurAfficheComponent ]
    })
    .compileComponents();
  }));

 
  it('should create', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    // récupérer l'instance du composant
    const compteurAffiche = fixture.debugElement.componentInstance;
    // vérifier que le composant est bien créer
    expect(compteurAffiche).toBeTruthy();
  });

  it('title of component should be "compteur app"', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    const compteurAffiche = fixture.debugElement.componentInstance;
    expect(compteurAffiche.title).toEqual('compteur app');
  });

  it('title of componenet in html should contain "compteur app"', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    // html du composant compilé par angular
    fixture.detectChanges();
    const rendered = fixture.debugElement.nativeElement;
    expect(rendered.querySelector('h3.apptitle').textContent).toContain('compteur app');
  });

  it('message of component should be "coucou"', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    const compteurAffiche = fixture.debugElement.componentInstance;
    expect(compteurAffiche.message).toEqual("coucou");
  });

  it('message in html should contain "coucou"', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    fixture.detectChanges();
    const rendered = fixture.debugElement.nativeElement;
    expect(rendered.querySelector('p.message').textContent).toContain("coucou"); 
  });

  it('compteur should start at 1, then 2 after increment', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    const compteurAffiche = fixture.debugElement.componentInstance;
    expect(compteurAffiche.compteur).toEqual(1);
    compteurAffiche.incrementeCompteur();
    expect(compteurAffiche.compteur).toEqual(2);
  });
  
  it('compteur in html should start at 1, then 2 after increment', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    const compteurAffiche = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    fixture.detectChanges();
    expect(rendered.querySelector("span#mon_compteur").textContent).toContain("1");
    compteurAffiche.incrementeCompteur();
    fixture.detectChanges();
    expect(rendered.querySelector("span#mon_compteur").textContent).toContain("2");  
  });

  it('should increment compteur if button clicked', () => {
    const fixture = TestBed.createComponent(CompteurAfficheComponent);
    const compteurAffiche = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    fixture.detectChanges();
    expect(rendered.querySelector("span#mon_compteur").textContent).toContain("1");
    // ici nous n'appelons pas directement la méthode d'increment
    // ous cliquons (simulons un click) sur le bouton dans le html 
    rendered.querySelector("button#inc_button").click();
    fixture.detectChanges();
    expect(compteurAffiche.compteur).toEqual(2);
    expect(rendered.querySelector("span#mon_compteur").textContent).toContain("2");  
  });


});
