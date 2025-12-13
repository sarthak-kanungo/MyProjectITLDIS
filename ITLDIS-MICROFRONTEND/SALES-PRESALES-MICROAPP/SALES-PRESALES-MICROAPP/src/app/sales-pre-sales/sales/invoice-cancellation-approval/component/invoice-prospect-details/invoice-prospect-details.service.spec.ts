import { TestBed } from '@angular/core/testing';

import { InvoiceProspectDetailsService } from './invoice-prospect-details.service';

describe('InvoiceProspectDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceProspectDetailsService = TestBed.get(InvoiceProspectDetailsService);
    expect(service).toBeTruthy();
  });
});
