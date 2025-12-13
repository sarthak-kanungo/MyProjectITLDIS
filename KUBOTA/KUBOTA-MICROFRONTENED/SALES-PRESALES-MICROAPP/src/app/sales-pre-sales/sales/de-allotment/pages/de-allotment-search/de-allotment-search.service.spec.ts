import { TestBed } from '@angular/core/testing';

import { DeAllotmentSearchService } from './de-allotment-search.service';

describe('DeAllotmentSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeAllotmentSearchService = TestBed.get(DeAllotmentSearchService);
    expect(service).toBeTruthy();
  });
});
