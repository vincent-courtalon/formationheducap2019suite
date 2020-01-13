import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffichageJeuxComponent } from './affichage-jeux.component';

describe('AffichageJeuxComponent', () => {
  let component: AffichageJeuxComponent;
  let fixture: ComponentFixture<AffichageJeuxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AffichageJeuxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AffichageJeuxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
