import { TestBed } from '@angular/core/testing';

import { ActivityProposalService } from './activity-proposal.service';

describe('ActivityProposalService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityProposalService = TestBed.get(ActivityProposalService);
    expect(service).toBeTruthy();
  });
});
