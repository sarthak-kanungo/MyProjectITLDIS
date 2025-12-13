import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectSurveyServiceHistoryComponent } from './direct-survey-service-history.component';

describe('DirectSurveyServiceHistoryComponent', () => {
  let component: DirectSurveyServiceHistoryComponent;
  let fixture: ComponentFixture<DirectSurveyServiceHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DirectSurveyServiceHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectSurveyServiceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
