import { TestBed } from '@angular/core/testing';

import { PartQuotationSearchWebService } from './part-quotation-search-web.service';

describe('PartQuotationSearchWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartQuotationSearchWebService = TestBed.get(PartQuotationSearchWebService);
    expect(service).toBeTruthy();
  });
});
