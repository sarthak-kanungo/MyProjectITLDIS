import { TestBed } from '@angular/core/testing';

import { BtbtSearchPageWebService } from './btbt-search-page-web.service';

describe('BtbtSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BtbtSearchPageWebService = TestBed.get(BtbtSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
