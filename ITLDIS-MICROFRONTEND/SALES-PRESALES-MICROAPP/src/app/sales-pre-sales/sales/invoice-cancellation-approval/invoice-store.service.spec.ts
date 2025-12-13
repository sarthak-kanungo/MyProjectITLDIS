import { TestBed } from '@angular/core/testing';

import { InvoiceStoreService } from './invoice-store.service';

describe('InvoiceStoreService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceStoreService = TestBed.get(InvoiceStoreService);
    expect(service).toBeTruthy();
  });
});
