import { TestBed } from '@angular/core/testing';

import { InvoiceFormAdaptorService } from './invoice-form-adaptor.service';

describe('InvoiceFormAdaptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceFormAdaptorService = TestBed.get(InvoiceFormAdaptorService);
    expect(service).toBeTruthy();
  });
});
