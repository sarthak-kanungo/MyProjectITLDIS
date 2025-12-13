import { TestBed } from '@angular/core/testing';

import { ClaimApprovalCreateService } from './claim-approval-create.service';

describe('ClaimApprovalCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClaimApprovalCreateService = TestBed.get(ClaimApprovalCreateService);
    expect(service).toBeTruthy();
  });
});
