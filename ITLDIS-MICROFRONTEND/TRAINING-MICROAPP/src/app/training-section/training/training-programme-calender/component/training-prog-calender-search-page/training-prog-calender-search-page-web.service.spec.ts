import { TestBed } from '@angular/core/testing';

import { TrainingProgrammeCalenderSearchPageWebService } from './training-prog-calender-search-page-web.service';

describe('TrainingProgrammeCalenderSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainingProgrammeCalenderSearchPageWebService = TestBed.get(TrainingProgrammeCalenderSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
