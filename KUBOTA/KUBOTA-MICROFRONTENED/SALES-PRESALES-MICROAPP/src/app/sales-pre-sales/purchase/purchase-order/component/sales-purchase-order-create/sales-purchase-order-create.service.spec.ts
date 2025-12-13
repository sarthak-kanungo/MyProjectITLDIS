import { TestBed } from '@angular/core/testing';

import { SalesPurchaseOrderCreateService } from './sales-purchase-order-create.service';

describe('SalesPurchaseOrderCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalesPurchaseOrderCreateService = TestBed.get(SalesPurchaseOrderCreateService);
    expect(service).toBeTruthy();
  });
});
