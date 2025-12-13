import { TestBed } from '@angular/core/testing';

import { SourceSearchPageWebService } from './source-search-page-web.service';

describe('SourceSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SourceSearchPageWebService = TestBed.get(SourceSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
