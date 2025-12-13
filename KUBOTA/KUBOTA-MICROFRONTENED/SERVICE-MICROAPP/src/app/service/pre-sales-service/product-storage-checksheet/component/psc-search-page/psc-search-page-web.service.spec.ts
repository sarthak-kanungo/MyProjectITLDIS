import { TestBed } from '@angular/core/testing';

import { PscSearchPageWebService } from './psc-search-page-web.service';

describe('PscSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PscSearchPageWebService = TestBed.get(PscSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
