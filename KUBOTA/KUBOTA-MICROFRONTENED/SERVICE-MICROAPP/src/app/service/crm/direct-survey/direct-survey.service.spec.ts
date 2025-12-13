import { TestBed } from '@angular/core/testing';

import { DirectSurveyService } from './direct-survey.service';

describe('DirectSurveyService', () => {
  let service: DirectSurveyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DirectSurveyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
