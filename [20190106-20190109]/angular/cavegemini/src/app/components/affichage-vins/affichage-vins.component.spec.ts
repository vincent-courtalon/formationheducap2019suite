import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AffichageVinsComponent } from './affichage-vins.component';

describe('AffichageVinsComponent', () => {
  let component: AffichageVinsComponent;
  let fixture: ComponentFixture<AffichageVinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AffichageVinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AffichageVinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
