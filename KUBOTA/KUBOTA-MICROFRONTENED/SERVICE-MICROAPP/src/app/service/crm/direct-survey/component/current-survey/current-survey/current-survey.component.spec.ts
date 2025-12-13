import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentSurveyComponent } from './current-survey.component';

describe('CurrentSurveyComponent', () => {
  let component: CurrentSurveyComponent;
  let fixture: ComponentFixture<CurrentSurveyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentSurveyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
