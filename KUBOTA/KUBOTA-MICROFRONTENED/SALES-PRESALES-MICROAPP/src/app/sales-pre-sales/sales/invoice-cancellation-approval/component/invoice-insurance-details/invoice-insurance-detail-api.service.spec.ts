import { TestBed } from '@angular/core/testing';

import { InvoiceInsuranceDetailApiService } from './invoice-insurance-detail-api.service';

describe('InvoiceInsuranceDetailApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceInsuranceDetailApiService = TestBed.get(InvoiceInsuranceDetailApiService);
    expect(service).toBeTruthy();
  });
});
