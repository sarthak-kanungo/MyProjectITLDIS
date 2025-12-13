import { TestBed } from '@angular/core/testing';

import { ServiceBookingSearchPageWebService } from './service-booking-search-page-web.service';

describe('ServiceBookingSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceBookingSearchPageWebService = TestBed.get(ServiceBookingSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
