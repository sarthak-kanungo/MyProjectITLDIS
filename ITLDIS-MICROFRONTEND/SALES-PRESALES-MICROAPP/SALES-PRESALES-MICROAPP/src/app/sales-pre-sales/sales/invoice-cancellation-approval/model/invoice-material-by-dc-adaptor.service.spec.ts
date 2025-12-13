import { TestBed } from '@angular/core/testing';

import { InvoiceMaterialByDcAdaptorService } from './invoice-material-by-dc-adaptor.service';

describe('InvoiceMaterialBydcAdaptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceMaterialByDcAdaptorService = TestBed.get(InvoiceMaterialByDcAdaptorService);
    expect(service).toBeTruthy();
  });
});
