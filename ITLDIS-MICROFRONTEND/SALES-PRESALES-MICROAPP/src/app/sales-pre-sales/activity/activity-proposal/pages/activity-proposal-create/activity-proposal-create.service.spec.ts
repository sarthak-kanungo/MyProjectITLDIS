import { TestBed } from '@angular/core/testing';

import { ActivityProposalCreateService } from './activity-proposal-create.service';

describe('ActivityProposalCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityProposalCreateService = TestBed.get(ActivityProposalCreateService);
    expect(service).toBeTruthy();
  });
});
