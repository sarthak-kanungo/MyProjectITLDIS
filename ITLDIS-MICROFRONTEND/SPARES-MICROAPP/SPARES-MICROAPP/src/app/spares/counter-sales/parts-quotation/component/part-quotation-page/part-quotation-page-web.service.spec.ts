import { TestBed } from '@angular/core/testing';

import { PartQuotationPageWebService } from './part-quotation-page-web.service';

describe('PartQuotationPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartQuotationPageWebService = TestBed.get(PartQuotationPageWebService);
    expect(service).toBeTruthy();
  });
});
