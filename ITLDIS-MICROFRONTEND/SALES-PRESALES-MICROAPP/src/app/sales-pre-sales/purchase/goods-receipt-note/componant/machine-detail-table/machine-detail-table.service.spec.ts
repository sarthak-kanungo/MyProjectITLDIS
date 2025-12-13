import { TestBed } from '@angular/core/testing';

import { MachineDetailTableService } from './machine-detail-table.service';

describe('MachineDetailTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MachineDetailTableService = TestBed.get(MachineDetailTableService);
    expect(service).toBeTruthy();
  });
});
