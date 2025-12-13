import { TestBed } from '@angular/core/testing';

import { SalesPurchaseOrderSearchContainerServiceService } from './sales-purchase-order-search-container-service.service';

describe('SalesPurchaseOrderSearchContainerServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalesPurchaseOrderSearchContainerServiceService = TestBed.get(SalesPurchaseOrderSearchContainerServiceService);
    expect(service).toBeTruthy();
  });
});
