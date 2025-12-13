import { TestBed } from '@angular/core/testing';

import { OPSService } from './ops.service';

describe('OPSService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OPSService = TestBed.get(OPSService);
    expect(service).toBeTruthy();
  });
});
