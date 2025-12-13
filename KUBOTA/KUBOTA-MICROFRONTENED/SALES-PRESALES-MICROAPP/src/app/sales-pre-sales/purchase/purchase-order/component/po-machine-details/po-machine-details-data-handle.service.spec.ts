import { TestBed } from '@angular/core/testing';

import { PoMachineDetailsDataHandleService } from './po-machine-details-data-handle.service';

describe('PoMachineDetailsDataHandleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PoMachineDetailsDataHandleService = TestBed.get(PoMachineDetailsDataHandleService);
    expect(service).toBeTruthy();
  });
});
