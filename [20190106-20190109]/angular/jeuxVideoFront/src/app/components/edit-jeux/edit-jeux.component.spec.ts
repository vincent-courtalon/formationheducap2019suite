import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditJeuxComponent } from './edit-jeux.component';

describe('EditJeuxComponent', () => {

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditJeuxComponent ]
    })
    .compileComponents();
  }));


  it('should create', () => {
    const fixture = TestBed.createComponent(EditJeuxComponent);
    const component = fixture.debugElement.componentInstance;
    expect(component).toBeTruthy();

  });
});
