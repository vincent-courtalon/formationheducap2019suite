import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltreJeuxComponent } from './filtre-jeux.component';

describe('FiltreJeuxComponent', () => {
  let component: FiltreJeuxComponent;
  let fixture: ComponentFixture<FiltreJeuxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltreJeuxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltreJeuxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
