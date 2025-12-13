import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelwiseServiceScheduleMasterSearchComponent } from './modelwise-service-schedule-master-search.component';

describe('ModelwiseServiceScheduleMasterSearchComponent', () => {
  let component: ModelwiseServiceScheduleMasterSearchComponent;
  let fixture: ComponentFixture<ModelwiseServiceScheduleMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModelwiseServiceScheduleMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelwiseServiceScheduleMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
