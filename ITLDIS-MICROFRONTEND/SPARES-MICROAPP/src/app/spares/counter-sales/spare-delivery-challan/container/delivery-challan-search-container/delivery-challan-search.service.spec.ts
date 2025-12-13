import { TestBed } from '@angular/core/testing';

import { DeliveryChallanSearchService } from './delivery-challan-search.service';

describe('DeliveryChallanSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryChallanSearchService = TestBed.get(DeliveryChallanSearchService);
    expect(service).toBeTruthy();
  });
});
