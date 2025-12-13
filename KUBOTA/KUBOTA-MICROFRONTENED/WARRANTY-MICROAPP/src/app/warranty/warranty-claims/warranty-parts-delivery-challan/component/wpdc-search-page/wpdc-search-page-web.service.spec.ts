import { TestBed } from '@angular/core/testing';

import { WpdcSearchPageWebService } from './wpdc-search-page-web.service';

describe('WpdcSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WpdcSearchPageWebService = TestBed.get(WpdcSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
