import { TestBed } from '@angular/core/testing';

import { PurchaseOrderCreatePageWebService } from './purchase-order-create-page-web.service';

describe('PurchaseOrderCreatePageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PurchaseOrderCreatePageWebService = TestBed.get(PurchaseOrderCreatePageWebService);
    expect(service).toBeTruthy();
  });
});
