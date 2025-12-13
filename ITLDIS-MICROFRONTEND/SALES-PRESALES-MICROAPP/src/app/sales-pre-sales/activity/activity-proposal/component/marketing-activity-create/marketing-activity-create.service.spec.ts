import { TestBed } from '@angular/core/testing';

import { MarketingActivityCreateService } from './marketing-activity-create.service';

describe('MarketingActivityCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MarketingActivityCreateService = TestBed.get(MarketingActivityCreateService);
    expect(service).toBeTruthy();
  });
});
