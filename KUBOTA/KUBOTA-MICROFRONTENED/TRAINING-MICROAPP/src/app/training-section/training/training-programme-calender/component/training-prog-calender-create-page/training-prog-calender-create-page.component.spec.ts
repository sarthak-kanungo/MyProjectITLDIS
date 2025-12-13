import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingProgrammeCalenderCreatePageComponent } from './training-prog-calender-create-page.component';

describe('TrainingProgrammeCalenderCreatePageComponent', () => {
  let component: TrainingProgrammeCalenderCreatePageComponent;
  let fixture: ComponentFixture<TrainingProgrammeCalenderCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainingProgrammeCalenderCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingProgrammeCalenderCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
