import { TestBed } from '@angular/core/testing';

import { MarketingActivityCreateContainerService } from './marketing-activity-create-container.service';

describe('MarketingActivityCreateContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MarketingActivityCreateContainerService = TestBed.get(MarketingActivityCreateContainerService);
    expect(service).toBeTruthy();
  });
});
