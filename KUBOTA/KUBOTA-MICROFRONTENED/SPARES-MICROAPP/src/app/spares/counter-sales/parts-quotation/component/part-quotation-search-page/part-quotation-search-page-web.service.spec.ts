import { TestBed } from '@angular/core/testing';

import { PartQuotationSearchPageWebService } from './part-quotation-search-page-web.service';

describe('PartQuotationSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartQuotationSearchPageWebService = TestBed.get(PartQuotationSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
