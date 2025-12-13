import { TestBed } from '@angular/core/testing';

import { CancelInvoiceFormService } from './cancel-invoice-form.service';

describe('CancelInvoiceFormService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CancelInvoiceFormService = TestBed.get(CancelInvoiceFormService);
    expect(service).toBeTruthy();
  });
});
