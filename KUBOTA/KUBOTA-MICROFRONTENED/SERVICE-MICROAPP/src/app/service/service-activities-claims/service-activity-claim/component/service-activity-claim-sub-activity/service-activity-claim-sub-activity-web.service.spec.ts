import { TestBed } from '@angular/core/testing';

import { ServiceActivityClaimSubActivityWebService } from './service-activity-claim-sub-activity-web.service';

describe('ServiceActivityClaimSubActivityWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceActivityClaimSubActivityWebService = TestBed.get(ServiceActivityClaimSubActivityWebService);
    expect(service).toBeTruthy();
  });
});
