import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltreJeuxComponent } from './filtre-jeux.component';
import { FormsModule } from '@angular/forms';

describe('FiltreJeuxComponent', () => {
 
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiltreJeuxComponent ],
      imports : [FormsModule]
    })
    .compileComponents();
  }));

  it('should create', () => {
    const fixture = TestBed.createComponent(FiltreJeuxComponent);
    const component = fixture.debugElement.componentInstance;
    expect(component).toBeTruthy();

  });
});
