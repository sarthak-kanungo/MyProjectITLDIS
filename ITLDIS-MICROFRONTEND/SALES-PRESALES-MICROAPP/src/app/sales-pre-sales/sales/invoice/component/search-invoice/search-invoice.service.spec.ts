import { TestBed } from '@angular/core/testing';

import { SearchInvoiceService } from './search-invoice.service';

describe('SearchInvoiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchInvoiceService = TestBed.get(SearchInvoiceService);
    expect(service).toBeTruthy();
  });
});
