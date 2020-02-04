import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeJeuxComponent } from './liste-jeux.component';
import { PaginationComponent } from 'ngx-bootstrap/pagination';
import { MockComponent } from "ng-mocks";
import { FormsModule } from '@angular/forms';
import { JeuxVideo } from 'src/app/metier/jeux-video';
import { Page } from 'src/app/metier/page';
import { Editeur } from 'src/app/metier/editeur';

describe('ListeJeuxComponent', () => {
 
  const page_JV_A : Page<JeuxVideo> = new Page<JeuxVideo>(
          [new JeuxVideo(1, "adibou et la physique quantique", new Date(),
                            new Editeur(1, "adibou", "adibou@gmail.com")
                            ,null, null),
          new JeuxVideo(1, "maestro et le corona virus pas sympa", new Date(),
                            new Editeur(1, "maestro", "maestro@gmail.com"),
                            null, null)
          ],
          2, 0, 10, 1, 2, true, true, false);


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeJeuxComponent, MockComponent(PaginationComponent)],
      imports : [FormsModule]
    })
    .compileComponents();
  }));


  it('should create', () => {
    const fixture = TestBed.createComponent(ListeJeuxComponent);
    const component = fixture.debugElement.componentInstance;
    expect(component).toBeTruthy();
  });

  it('should display a table with 2 lines', () => {
    const fixture = TestBed.createComponent(ListeJeuxComponent);
    const component = fixture.debugElement.componentInstance as ListeJeuxComponent;
    const rendered = fixture.debugElement.nativeElement;
    component.jeuxvideos = page_JV_A;
    fixture.detectChanges();
    expect(component.pageJv.size).toEqual(page_JV_A.size);
    expect(component.currentPage).toEqual(page_JV_A.number + 1);
    const trs = rendered.querySelectorAll('table.table tbody tr');
    expect(trs.length).toEqual(2);
    expect(trs[0].querySelectorAll('td')[0].textContent).toEqual("1");
    expect(trs[1].querySelectorAll('td')[1].textContent).toEqual("maestro et le corona virus pas sympa");
    expect(trs[1].querySelectorAll('td')[3].textContent).toEqual("maestro");
  });

  it ('should output changePage when page changed', () => {
    const fixture = TestBed.createComponent(ListeJeuxComponent);
    const component = fixture.debugElement.componentInstance as ListeJeuxComponent;
    component.jeuxvideos = page_JV_A;
    fixture.detectChanges();
    spyOn(component.changePage, "emit").and.callThrough();
 /*   spyOn(component.changePage, "emit").and.callFake( v  => {
      expect(v).toEqual("2");
    });*/
    component.pageChanged(2);

    expect(component.changePage.emit).toHaveBeenCalledTimes(1);
  });



});
