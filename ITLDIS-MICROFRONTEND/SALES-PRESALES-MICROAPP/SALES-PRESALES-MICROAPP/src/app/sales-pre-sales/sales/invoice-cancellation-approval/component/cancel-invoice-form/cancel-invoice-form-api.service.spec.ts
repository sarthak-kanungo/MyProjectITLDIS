import { TestBed } from '@angular/core/testing';

import { CancelInvoiceFormApiService } from './cancel-invoice-form-api.service';

describe('CancelInvoiceFormApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CancelInvoiceFormApiService = TestBed.get(CancelInvoiceFormApiService);
    expect(service).toBeTruthy();
  });
});
