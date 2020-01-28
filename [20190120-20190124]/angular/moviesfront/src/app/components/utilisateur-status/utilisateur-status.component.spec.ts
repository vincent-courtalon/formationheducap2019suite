import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtilisateurStatusComponent } from './utilisateur-status.component';

describe('UtilisateurStatusComponent', () => {
  let component: UtilisateurStatusComponent;
  let fixture: ComponentFixture<UtilisateurStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtilisateurStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtilisateurStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
