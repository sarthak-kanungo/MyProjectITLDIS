import { TestBed } from '@angular/core/testing';

import { MarketingActivitySearchContainerService } from './marketing-activity-search-container.service';

describe('MarketingActivitySearchContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MarketingActivitySearchContainerService = TestBed.get(MarketingActivitySearchContainerService);
    expect(service).toBeTruthy();
  });
});
