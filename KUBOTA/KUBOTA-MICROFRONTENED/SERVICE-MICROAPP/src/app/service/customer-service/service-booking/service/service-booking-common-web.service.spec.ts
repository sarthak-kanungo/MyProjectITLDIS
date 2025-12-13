import { TestBed } from '@angular/core/testing';

import { ServiceBookingCommonWebService } from './service-booking-common-web.service';

describe('ServiceBookingCommonWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceBookingCommonWebService = TestBed.get(ServiceBookingCommonWebService);
    expect(service).toBeTruthy();
  });
});
