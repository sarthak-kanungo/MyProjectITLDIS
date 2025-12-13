import { TestBed } from '@angular/core/testing';

import { VehicleRegistrationDetailsService } from './vehicle-registration-details.service';

describe('VehicleRegistrationDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VehicleRegistrationDetailsService = TestBed.get(VehicleRegistrationDetailsService);
    expect(service).toBeTruthy();
  });
});
