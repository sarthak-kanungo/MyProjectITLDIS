import { TestBed } from '@angular/core/testing';

import { WorkShopBillsearchWebService } from './work-shop-billsearch-web.service';

describe('WorkShopBillsearchWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkShopBillsearchWebService = TestBed.get(WorkShopBillsearchWebService);
    expect(service).toBeTruthy();
  });
});
