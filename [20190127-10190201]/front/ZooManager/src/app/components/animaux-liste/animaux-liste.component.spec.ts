import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimauxListeComponent } from './animaux-liste.component';

describe('AnimauxListeComponent', () => {
  let component: AnimauxListeComponent;
  let fixture: ComponentFixture<AnimauxListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnimauxListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimauxListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
