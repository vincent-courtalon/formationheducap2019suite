import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltreJeuxComponent } from './filtre-jeux.component';
import { FormsModule } from '@angular/forms';
import { Editeur } from 'src/app/metier/editeur';

describe('FiltreJeuxComponent', () => {
 
  const editeurs : Editeur[] = [new Editeur(1, "adibou", "adibou@educ.com", null),
                               new Editeur(2, "maestro", "maestro@editeur.com", null)];

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltreJeuxComponent ],
      imports : [FormsModule]
    })
    .compileComponents();
  }));

  it('should create', () => {
    const fixture = TestBed.createComponent(FiltreJeuxComponent);
    const component = fixture.debugElement.componentInstance;
    expect(component).toBeTruthy();

  });

  it('sould display a select with 3 choices', () => {
    const fixture = TestBed.createComponent(FiltreJeuxComponent);
    const component = fixture.debugElement.componentInstance as FiltreJeuxComponent;
    const rendered = fixture.debugElement.nativeElement;
    component.editeurs = editeurs;
    fixture.detectChanges();
    expect(rendered.querySelectorAll('select#choixEditeur option').length).toEqual(3);
    expect(rendered.querySelectorAll('select#choixEditeur option')[1].textContent).toContain("adibou");
  });

  it('should emit when option selected', () => {
    const fixture = TestBed.createComponent(FiltreJeuxComponent);
    const component = fixture.debugElement.componentInstance as FiltreJeuxComponent;
    const rendered = fixture.debugElement.nativeElement;
    component.editeurs = editeurs;
    fixture.detectChanges();
    // callThrough permet de rapeller la méthode original qui a été interceptée
    // par défaut, ce n'est pas le cas (onChoixEditeur ne sera pas rappelée) 
    spyOn(component, "onChoixEditeur").and.callThrough();
    spyOn(component.filtreEditeur, "emit").and.callThrough();

    const listSelect = rendered.querySelector('select#choixEditeur');
    listSelect.value = 1;
    listSelect.dispatchEvent(new Event('ngModelChange'));

    expect(component.onChoixEditeur).toHaveBeenCalledTimes(1);
    expect(component.filtreEditeur.emit).toHaveBeenCalledTimes(1);



  })

});
