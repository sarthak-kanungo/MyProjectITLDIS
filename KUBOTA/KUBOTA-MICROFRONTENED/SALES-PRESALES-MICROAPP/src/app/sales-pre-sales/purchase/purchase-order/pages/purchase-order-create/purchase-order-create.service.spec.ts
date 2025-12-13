import { TestBed } from '@angular/core/testing';

import { PurchaseOrderCreateService } from './purchase-order-create.service';

describe('PurchaseOrderCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PurchaseOrderCreateService = TestBed.get(PurchaseOrderCreateService);
    expect(service).toBeTruthy();
  });
});
