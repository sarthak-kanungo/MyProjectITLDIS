import { TestBed } from '@angular/core/testing';

import { SearchPageWebService } from './search-page-web.service';

describe('SearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchPageWebService = TestBed.get(SearchPageWebService);
    expect(service).toBeTruthy();
  });
});
