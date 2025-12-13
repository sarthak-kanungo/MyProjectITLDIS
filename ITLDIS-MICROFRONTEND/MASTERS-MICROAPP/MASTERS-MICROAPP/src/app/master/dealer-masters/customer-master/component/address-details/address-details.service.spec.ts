import { TestBed } from '@angular/core/testing';

import { AddressDetailsService } from './address-details.service';

describe('AddressDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddressDetailsService = TestBed.get(AddressDetailsService);
    expect(service).toBeTruthy();
  });
});
