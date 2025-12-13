import { TestBed } from '@angular/core/testing';

import { DeliveryChallansService } from './delivery-challans.service';

describe('DeliveryChallansService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryChallansService = TestBed.get(DeliveryChallansService);
    expect(service).toBeTruthy();
  });
});
