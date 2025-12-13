import { TestBed } from '@angular/core/testing';

import { ServiceClaimApprovalSearchServiceService } from './service-claim-approval-search-service.service';

describe('ServiceClaimApprovalSearchServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceClaimApprovalSearchServiceService = TestBed.get(ServiceClaimApprovalSearchServiceService);
    expect(service).toBeTruthy();
  });
});
