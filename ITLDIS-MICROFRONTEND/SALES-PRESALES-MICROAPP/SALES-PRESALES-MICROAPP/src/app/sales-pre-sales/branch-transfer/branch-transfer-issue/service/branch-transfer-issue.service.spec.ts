import { TestBed } from '@angular/core/testing';

import { BranchTransferIssueService } from './branch-transfer-issue.service';

describe('BranchTransferIssueService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BranchTransferIssueService = TestBed.get(BranchTransferIssueService);
    expect(service).toBeTruthy();
  });
});
