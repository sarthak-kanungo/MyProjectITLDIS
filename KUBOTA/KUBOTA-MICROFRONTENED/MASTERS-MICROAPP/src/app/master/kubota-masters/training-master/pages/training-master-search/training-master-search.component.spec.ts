import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingMasterSearchComponent } from './training-master-search.component';

describe('TrainingMasterSearchComponent', () => {
  let component: TrainingMasterSearchComponent;
  let fixture: ComponentFixture<TrainingMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
