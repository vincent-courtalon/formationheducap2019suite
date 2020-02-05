import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZooListeComponent } from './zoo-liste.component';

describe('ZooListeComponent', () => {
  let component: ZooListeComponent;
  let fixture: ComponentFixture<ZooListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZooListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZooListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
