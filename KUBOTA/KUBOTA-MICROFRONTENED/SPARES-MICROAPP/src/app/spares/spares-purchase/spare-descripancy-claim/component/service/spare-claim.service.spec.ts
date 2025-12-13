import { TestBed } from '@angular/core/testing';

import { SpareClaimService } from './spare-claim.service';

describe('SpareClaimService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SpareClaimService = TestBed.get(SpareClaimService);
    expect(service).toBeTruthy();
  });
});
