import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectSurveyCreateComponent } from './direct-survey-create.component';

describe('DirectSurveyCreateComponent', () => {
  let component: DirectSurveyCreateComponent;
  let fixture: ComponentFixture<DirectSurveyCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DirectSurveyCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectSurveyCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
