import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltreVinsComponent } from './filtre-vins.component';

describe('FiltreVinsComponent', () => {
  let component: FiltreVinsComponent;
  let fixture: ComponentFixture<FiltreVinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltreVinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltreVinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
