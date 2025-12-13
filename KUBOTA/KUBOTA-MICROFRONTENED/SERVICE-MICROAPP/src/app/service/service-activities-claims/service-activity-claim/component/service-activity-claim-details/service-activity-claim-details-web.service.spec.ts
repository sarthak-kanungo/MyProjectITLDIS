import { TestBed } from '@angular/core/testing';

import { ServiceActivityClaimDetailsWebService } from './service-activity-claim-details-web.service';

describe('ServiceActivityClaimDetailsWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceActivityClaimDetailsWebService = TestBed.get(ServiceActivityClaimDetailsWebService);
    expect(service).toBeTruthy();
  });
});
