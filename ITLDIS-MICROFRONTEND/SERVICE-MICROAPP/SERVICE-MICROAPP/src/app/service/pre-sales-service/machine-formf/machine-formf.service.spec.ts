import { TestBed } from '@angular/core/testing';

import { MachineFormfService } from './machine-formf.service';

describe('MachineFormfService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MachineFormfService = TestBed.get(MachineFormfService);
    expect(service).toBeTruthy();
  });
});
