import { TestBed } from '@angular/core/testing';

import { TrainingnominationSearchPageWebService } from './training-nomination-search-page-web.service';

describe('TrainingnominationSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainingnominationSearchPageWebService = TestBed.get(TrainingnominationSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
