import { TestBed } from '@angular/core/testing';

import { TrainingnominationService } from './training-nomination.service';

describe('TrainingProgCalenderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainingnominationService = TestBed.get(TrainingnominationService);
    expect(service).toBeTruthy();
  });
});
