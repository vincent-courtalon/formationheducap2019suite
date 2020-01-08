import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditVinComponent } from './edit-vin.component';

describe('EditVinComponent', () => {
  let component: EditVinComponent;
  let fixture: ComponentFixture<EditVinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditVinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditVinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
