import { TestBed } from '@angular/core/testing';

import { DeliveryChallanApiService } from './delivery-challan-api.service';

describe('DeliveryChallanApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryChallanApiService = TestBed.get(DeliveryChallanApiService);
    expect(service).toBeTruthy();
  });
});
