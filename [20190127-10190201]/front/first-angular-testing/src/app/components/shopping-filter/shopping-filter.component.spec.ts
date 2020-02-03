import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingFilterComponent } from './shopping-filter.component';
import { FormsModule } from '@angular/forms';

describe('ShoppingFilterComponent', () => {
  let component: ShoppingFilterComponent;
  let fixture: ComponentFixture<ShoppingFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingFilterComponent ],
      imports : [FormsModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
