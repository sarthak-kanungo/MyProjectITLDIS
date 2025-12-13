import { TestBed } from '@angular/core/testing';

import { StoreSearchPageWebService } from './store-search-page-web.service';

describe('StoreSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StoreSearchPageWebService = TestBed.get(StoreSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
