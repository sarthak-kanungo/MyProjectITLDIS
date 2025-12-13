import { TestBed } from '@angular/core/testing';

import { InvoiceApiService } from './invoice-api.service';

describe('InvoiceApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceApiService = TestBed.get(InvoiceApiService);
    expect(service).toBeTruthy();
  });
});
