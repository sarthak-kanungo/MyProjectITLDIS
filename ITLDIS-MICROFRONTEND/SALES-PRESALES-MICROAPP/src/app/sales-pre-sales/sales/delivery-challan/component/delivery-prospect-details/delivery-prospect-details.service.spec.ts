import { TestBed } from '@angular/core/testing';

import { DeliveryProspectDetailsService } from './delivery-prospect-details.service';

describe('DeliveryProspectDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliveryProspectDetailsService = TestBed.get(DeliveryProspectDetailsService);
    expect(service).toBeTruthy();
  });
});
