import { TestBed } from '@angular/core/testing';

import { WarrentyClaimRequestSearchWebService } from './warrenty-claim-request-search-web.service';

describe('WarrentyClaimRequestSearchWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WarrentyClaimRequestSearchWebService = TestBed.get(WarrentyClaimRequestSearchWebService);
    expect(service).toBeTruthy();
  });
});
