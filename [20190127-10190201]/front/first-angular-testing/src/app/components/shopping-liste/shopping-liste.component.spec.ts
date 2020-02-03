import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListeComponent } from './shopping-liste.component';
import { FormsModule } from '@angular/forms';
import { ShoppingSummaryComponent } from '../shopping-summary/shopping-summary.component';
import { MockComponent } from "ng-mocks";
import { By } from '@angular/platform-browser';
import { ShoppingFilterComponent } from '../shopping-filter/shopping-filter.component';

describe('ShoppingListeComponent', () => {


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingListeComponent,
                      MockComponent(ShoppingSummaryComponent),
                      MockComponent(ShoppingFilterComponent) ],
      imports: [FormsModule]
    })
    .compileComponents();
  }));




  it('should have 3 items in ul list of achats and first is "fraise tagada"', () => {
    const fixture = TestBed.createComponent(ShoppingListeComponent);
    const ShoppingComponent = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    fixture.detectChanges();
    let liste = rendered.querySelectorAll("ul#listeCourse li");
    expect(liste[0].textContent).toContain("fraise tagada");
    expect(liste.length).toEqual(3);
  });

  it('nouvelAchat should contain typed text from input', () => {
    const fixture = TestBed.createComponent(ShoppingListeComponent);
    const ShoppingComponent = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    fixture.detectChanges();
    const saisie =rendered.querySelector('input[name="nouvelAchat"]');
    saisie.value = "miel des carpathes";
    saisie.dispatchEvent(new Event('input'));
    fixture.detectChanges();
    expect(ShoppingComponent.nouvelAchat).toEqual("miel des carpathes");
  });

  it('liste should contain new item from input', () => {
    const fixture = TestBed.createComponent(ShoppingListeComponent);
    const ShoppingComponent = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    fixture.detectChanges();
    const saisie =rendered.querySelector('input[name="nouvelAchat"]');
    saisie.value = "miel des carpathes";
    saisie.dispatchEvent(new Event('input'));
    //fixture.detectChanges();
    const bouton = rendered.querySelector("button#addButton");
    bouton.click();
    fixture.detectChanges();
    const liste = rendered.querySelectorAll("ul#listeCourse li");
    expect(liste.length).toEqual(4);
    expect(liste[3].textContent).toContain("miel des carpathes");

  })

  it('liste summary should receive list size when updated', () => {
    const fixture = TestBed.createComponent(ShoppingListeComponent);
    const ShoppingComponent = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    const mockedChildComponent = fixture.debugElement
                                        .query(By.css('app-shopping-summary'))
                                        .componentInstance as ShoppingSummaryComponent;

    fixture.detectChanges();
    // je vérifie que le composant
    expect(mockedChildComponent.totalCount).toEqual(3);

    const saisie =rendered.querySelector('input[name="nouvelAchat"]');
    saisie.value = "miel des carpathes";
    saisie.dispatchEvent(new Event('input'));
    const bouton = rendered.querySelector("button#addButton");
    bouton.click();
    fixture.detectChanges();
    expect(mockedChildComponent.totalCount).toEqual(4);

  });

  it('filter should emit to FiltrerListe', () => {
    const fixture = TestBed.createComponent(ShoppingListeComponent);
    const shoppingComponent = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    const mockedChildComponent = fixture.debugElement
                                        .query(By.css('app-shopping-filter'))
                                        .componentInstance as ShoppingFilterComponent;

    spyOn(shoppingComponent, 'filtrerListe').and.returnValue("fraise");

    mockedChildComponent.filterTerm.emit("fraise");

    expect(shoppingComponent.filtrerListe).toHaveBeenCalledWith("fraise");
  });

  it('filter should correctly filter list', () => {
    const fixture = TestBed.createComponent(ShoppingListeComponent);
    const shoppingComponent = fixture.debugElement.componentInstance;
    const rendered = fixture.debugElement.nativeElement;
    const mockedChildComponent = fixture.debugElement
                                        .query(By.css('app-shopping-filter'))
                                        .componentInstance as ShoppingFilterComponent;

    spyOn(shoppingComponent, 'filtrerListe').and.callThrough();

    mockedChildComponent.filterTerm.emit("fraise");
    fixture.detectChanges();

    const liste = rendered.querySelectorAll("ul#listeCourse li");
    expect(liste.length).toEqual(1);
    expect(liste[0].textContent).toContain("fraise tagada");
    expect(shoppingComponent.filtrerListe).toHaveBeenCalledWith("fraise");

  });


});
