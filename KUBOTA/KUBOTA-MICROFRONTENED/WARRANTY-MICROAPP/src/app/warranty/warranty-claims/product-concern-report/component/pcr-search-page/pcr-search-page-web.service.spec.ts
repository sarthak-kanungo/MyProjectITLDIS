import { TestBed } from '@angular/core/testing';

import { PcrSearchPageWebService } from './pcr-search-page-web.service';

describe('PcrSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PcrSearchPageWebService = TestBed.get(PcrSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
