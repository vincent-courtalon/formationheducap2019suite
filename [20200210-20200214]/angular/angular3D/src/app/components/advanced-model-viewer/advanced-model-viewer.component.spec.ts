import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedModelViewerComponent } from './advanced-model-viewer.component';

describe('AdvancedModelViewerComponent', () => {
  let component: AdvancedModelViewerComponent;
  let fixture: ComponentFixture<AdvancedModelViewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvancedModelViewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedModelViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
