import { TestBed } from '@angular/core/testing';

import { DeliveryInsuranceDetailsService } from './delivery-insurance-details.service';

describe('DeliveryInsuranceDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryInsuranceDetailsService = TestBed.get(DeliveryInsuranceDetailsService);
    expect(service).toBeTruthy();
  });
});
