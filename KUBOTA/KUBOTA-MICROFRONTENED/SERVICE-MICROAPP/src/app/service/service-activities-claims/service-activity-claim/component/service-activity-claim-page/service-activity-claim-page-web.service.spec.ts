import { TestBed } from '@angular/core/testing';

import { ServiceActivityClaimPageWebService } from './service-activity-claim-page-web.service';

describe('ServiceActivityClaimPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceActivityClaimPageWebService = TestBed.get(ServiceActivityClaimPageWebService);
    expect(service).toBeTruthy();
  });
});
