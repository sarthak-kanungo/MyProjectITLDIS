import { TestBed } from '@angular/core/testing';

import { PartyMasterPageService } from './party-master-page.service';

describe('PartyMasterPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartyMasterPageService = TestBed.get(PartyMasterPageService);
    expect(service).toBeTruthy();
  });
});
