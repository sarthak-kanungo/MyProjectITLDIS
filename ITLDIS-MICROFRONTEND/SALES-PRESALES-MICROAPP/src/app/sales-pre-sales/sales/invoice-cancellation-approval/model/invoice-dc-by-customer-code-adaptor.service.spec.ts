import { TestBed } from '@angular/core/testing';

import { InvoiceDcByCustomerCodeAdaptorService } from './invoice-dc-by-customer-code-adaptor.service';

describe('InvoiceDcByCustomerCodeAdaptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceDcByCustomerCodeAdaptorService = TestBed.get(InvoiceDcByCustomerCodeAdaptorService);
    expect(service).toBeTruthy();
  });
});
