import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeJeuxComponent } from './liste-jeux.component';
import { PaginationComponent } from 'ngx-bootstrap/pagination';
import { MockComponent } from "ng-mocks";
import { FormsModule } from '@angular/forms';

describe('ListeJeuxComponent', () => {
 
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
});
