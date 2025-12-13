import { TestBed } from '@angular/core/testing';

import { SalesPreSalesCommonService } from './sales-pre-sales-common.service';

describe('SalesPreSalesCommonService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalesPreSalesCommonService = TestBed.get(SalesPreSalesCommonService);
    expect(service).toBeTruthy();
  });
});
