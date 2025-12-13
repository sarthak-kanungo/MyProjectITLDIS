import { TestBed } from '@angular/core/testing';

import { MarketingActivitySearchService } from './marketing-activity-search.service';

describe('MarketingActivitySearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MarketingActivitySearchService = TestBed.get(MarketingActivitySearchService);
    expect(service).toBeTruthy();
  });
});
