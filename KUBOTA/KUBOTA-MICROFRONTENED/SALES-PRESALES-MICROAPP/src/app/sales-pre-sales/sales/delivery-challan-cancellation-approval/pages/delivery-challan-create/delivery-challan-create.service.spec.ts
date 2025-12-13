import { TestBed } from '@angular/core/testing';

import { DeliveryChallanCreateService } from './delivery-challan-create.service';

describe('DeliveryChallanCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryChallanCreateService = TestBed.get(DeliveryChallanCreateService);
    expect(service).toBeTruthy();
  });
});
