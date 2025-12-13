import { TestBed } from '@angular/core/testing';

import { DeliveryChallanService } from './delivery-challan.service';

describe('DeliveryChallanService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryChallanService = TestBed.get(DeliveryChallanService);
    expect(service).toBeTruthy();
  });
});
