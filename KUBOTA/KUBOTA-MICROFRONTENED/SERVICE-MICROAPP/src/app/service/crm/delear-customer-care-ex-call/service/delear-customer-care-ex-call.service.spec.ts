import { TestBed } from '@angular/core/testing';

import { DelearCustomerCareExCallService } from './delear-customer-care-ex-call.service';

describe('DelearCustomerCareExCallService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DelearCustomerCareExCallService = TestBed.get(DelearCustomerCareExCallService);
    expect(service).toBeTruthy();
  });
});
