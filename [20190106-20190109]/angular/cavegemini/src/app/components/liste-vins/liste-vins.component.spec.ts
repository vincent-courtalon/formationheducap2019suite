import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeVinsComponent } from './liste-vins.component';

describe('ListeVinsComponent', () => {
  let component: ListeVinsComponent;
  let fixture: ComponentFixture<ListeVinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeVinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeVinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
