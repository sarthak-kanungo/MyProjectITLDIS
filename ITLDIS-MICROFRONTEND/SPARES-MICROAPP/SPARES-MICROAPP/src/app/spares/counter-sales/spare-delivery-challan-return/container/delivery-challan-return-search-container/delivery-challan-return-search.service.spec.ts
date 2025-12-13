import { TestBed } from '@angular/core/testing';

import { DeliveryChallanReturnSearchService } from './delivery-challan-return-search.service';

describe('DeliveryChallanReturnSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryChallanReturnSearchService = TestBed.get(DeliveryChallanReturnSearchService);
    expect(service).toBeTruthy();
  });
});
