import { TestBed } from '@angular/core/testing';

import { SalesInvoiceCancelSearchService } from './sales-invoice-cancel-search.service';

describe('SalesInvoiceCancelSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalesInvoiceCancelSearchService = TestBed.get(SalesInvoiceCancelSearchService);
    expect(service).toBeTruthy();
  });
});
