import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeJeuxComponent } from './liste-jeux.component';

describe('ListeJeuxComponent', () => {
  let component: ListeJeuxComponent;
  let fixture: ComponentFixture<ListeJeuxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeJeuxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeJeuxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
