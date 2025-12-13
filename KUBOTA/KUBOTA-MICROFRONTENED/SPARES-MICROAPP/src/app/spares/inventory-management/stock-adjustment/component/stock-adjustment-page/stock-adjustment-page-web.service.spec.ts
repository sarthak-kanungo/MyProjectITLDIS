import { TestBed } from '@angular/core/testing';

import { StockAdjustmentPageWebService } from './stock-adjustment-page-web.service';

describe('StockAdjustmentPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StockAdjustmentPageWebService = TestBed.get(StockAdjustmentPageWebService);
    expect(service).toBeTruthy();
  });
});
