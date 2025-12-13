import { TestBed } from '@angular/core/testing';

import { SparesSalesInvoiceWebService } from './spares-sales-invoice-web.service';

describe('SparesSalesInvoiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SparesSalesInvoiceWebService = TestBed.get(SparesSalesInvoiceWebService);
    expect(service).toBeTruthy();
  });
});
