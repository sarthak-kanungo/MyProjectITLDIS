import { TestBed } from '@angular/core/testing';

import { ActivityClaimApprovalCommonWebService } from './activity-claim-approval-common-web.service';

describe('ActivityClaimApprovalCommonWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityClaimApprovalCommonWebService = TestBed.get(ActivityClaimApprovalCommonWebService);
    expect(service).toBeTruthy();
  });
});
