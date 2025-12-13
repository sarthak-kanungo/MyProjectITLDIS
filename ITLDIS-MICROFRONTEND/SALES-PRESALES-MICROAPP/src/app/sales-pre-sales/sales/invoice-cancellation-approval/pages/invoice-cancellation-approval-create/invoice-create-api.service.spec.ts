import { TestBed } from '@angular/core/testing';

import { InvoiceCreateApiService } from './invoice-create-api.service';

describe('InvoiceCreateApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceCreateApiService = TestBed.get(InvoiceCreateApiService);
    expect(service).toBeTruthy();
  });
});
