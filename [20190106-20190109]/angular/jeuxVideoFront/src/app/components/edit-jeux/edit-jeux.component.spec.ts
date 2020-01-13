import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditJeuxComponent } from './edit-jeux.component';

describe('EditJeuxComponent', () => {
  let component: EditJeuxComponent;
  let fixture: ComponentFixture<EditJeuxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditJeuxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditJeuxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
