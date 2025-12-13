import { TestBed } from '@angular/core/testing';

import { IncentiveSchemeClaimService } from './incentive-scheme-claim.service';

describe('IncentiveSchemeClaimService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IncentiveSchemeClaimService = TestBed.get(IncentiveSchemeClaimService);
    expect(service).toBeTruthy();
  });
});
