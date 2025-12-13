import { TestBed } from '@angular/core/testing';

import { MachineDescripancyCommonService } from './machine-descripancy-common.service';

describe('MachineDescripancyCommonService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MachineDescripancyCommonService = TestBed.get(MachineDescripancyCommonService);
    expect(service).toBeTruthy();
  });
});
