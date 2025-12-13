import { TestBed } from '@angular/core/testing';

import { PurchaseOrderSearchWebService } from './purchase-order-search-web.service';

describe('PurchaseOrderSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PurchaseOrderSearchWebService = TestBed.get(PurchaseOrderSearchWebService);
    expect(service).toBeTruthy();
  });
});
