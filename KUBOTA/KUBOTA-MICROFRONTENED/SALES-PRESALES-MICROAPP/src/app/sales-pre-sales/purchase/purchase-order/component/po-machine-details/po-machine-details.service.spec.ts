import { TestBed } from '@angular/core/testing';

import { PoMachineDetailsService } from './po-machine-details.service';

describe('PoMachineDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PoMachineDetailsService = TestBed.get(PoMachineDetailsService);
    expect(service).toBeTruthy();
  });
});
