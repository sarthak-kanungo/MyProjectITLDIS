import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDirectSurveyComponent } from './create-direct-survey.component';

describe('CreateDirectSurveyComponent', () => {
  let component: CreateDirectSurveyComponent;
  let fixture: ComponentFixture<CreateDirectSurveyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateDirectSurveyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateDirectSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
