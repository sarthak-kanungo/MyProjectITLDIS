import { TestBed } from '@angular/core/testing';

import { PartyDetailsService } from './party-details.service';

describe('PartyDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartyDetailsService = TestBed.get(PartyDetailsService);
    expect(service).toBeTruthy();
  });
});
