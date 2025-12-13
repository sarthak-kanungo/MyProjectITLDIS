import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectSurveyMachineDetailComponent } from './direct-survey-machine-detail.component';

describe('DirectSurveyMachineDetailComponent', () => {
  let component: DirectSurveyMachineDetailComponent;
  let fixture: ComponentFixture<DirectSurveyMachineDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DirectSurveyMachineDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectSurveyMachineDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
