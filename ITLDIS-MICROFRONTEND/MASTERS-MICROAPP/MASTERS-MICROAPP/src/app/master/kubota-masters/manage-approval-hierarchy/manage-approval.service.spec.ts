import { TestBed } from '@angular/core/testing';

import { ManageApprovalService } from './manage-approval.service';

describe('ManageApprovalService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ManageApprovalService = TestBed.get(ManageApprovalService);
    expect(service).toBeTruthy();
  });
});
