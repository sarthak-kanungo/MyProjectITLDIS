import { TestBed } from '@angular/core/testing';

import { ServiceBookingSearchWebService } from './service-booking-search-web.service';

describe('ServiceBookingSearchWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceBookingSearchWebService = TestBed.get(ServiceBookingSearchWebService);
    expect(service).toBeTruthy();
  });
});
