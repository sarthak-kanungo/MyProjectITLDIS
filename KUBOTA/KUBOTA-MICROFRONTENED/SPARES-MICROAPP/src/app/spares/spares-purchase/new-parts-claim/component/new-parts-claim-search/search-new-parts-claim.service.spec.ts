import { TestBed } from '@angular/core/testing';

import { SearchNewPartsClaimService } from './search-new-parts-claim.service';

describe('SearchNewPartsClaimService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchNewPartsClaimService = TestBed.get(SearchNewPartsClaimService);
    expect(service).toBeTruthy();
  });
});
