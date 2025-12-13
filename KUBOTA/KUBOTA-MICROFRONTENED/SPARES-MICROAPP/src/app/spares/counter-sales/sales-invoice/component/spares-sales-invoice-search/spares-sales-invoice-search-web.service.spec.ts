import { TestBed } from '@angular/core/testing';

import { SparesSalesInvoiceSearchWebService } from './spares-sales-invoice-search-web.service';

describe('SparesSalesInvoiceSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SparesSalesInvoiceSearchWebService = TestBed.get(SparesSalesInvoiceSearchWebService);
    expect(service).toBeTruthy();
  });
});
