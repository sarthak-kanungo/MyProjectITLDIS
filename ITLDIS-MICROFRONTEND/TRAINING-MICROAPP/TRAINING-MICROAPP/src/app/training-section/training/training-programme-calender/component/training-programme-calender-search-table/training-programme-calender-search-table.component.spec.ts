import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingProgrammeCalenderSearchTableComponent } from './training-programme-calender-search-table.component';

describe('TrainingProgrammeCalenderSearchTableComponent', () => {
  let component: TrainingProgrammeCalenderSearchTableComponent;
  let fixture: ComponentFixture<TrainingProgrammeCalenderSearchTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingProgrammeCalenderSearchTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingProgrammeCalenderSearchTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
