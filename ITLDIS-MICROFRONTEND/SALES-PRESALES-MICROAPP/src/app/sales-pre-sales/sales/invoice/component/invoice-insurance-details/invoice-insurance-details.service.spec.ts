import { TestBed } from '@angular/core/testing';

import { InvoiceInsuranceDetailsService } from './invoice-insurance-details.service';

describe('InvoiceInsuranceDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceInsuranceDetailsService = TestBed.get(InvoiceInsuranceDetailsService);
    expect(service).toBeTruthy();
  });
});
