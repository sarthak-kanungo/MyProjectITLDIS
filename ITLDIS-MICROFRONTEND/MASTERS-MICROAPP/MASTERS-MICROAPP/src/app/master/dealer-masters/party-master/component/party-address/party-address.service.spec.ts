import { TestBed } from '@angular/core/testing';

import { PartyAddressService } from './party-address.service';

describe('PartyAddressService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartyAddressService = TestBed.get(PartyAddressService);
    expect(service).toBeTruthy();
  });
});
