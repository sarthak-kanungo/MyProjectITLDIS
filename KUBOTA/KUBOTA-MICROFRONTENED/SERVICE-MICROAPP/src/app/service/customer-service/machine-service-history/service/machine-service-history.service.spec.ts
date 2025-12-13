import { TestBed } from '@angular/core/testing';

import { MachineServiceHistoryService } from './machine-service-history.service';

describe('MachineServiceHistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MachineServiceHistoryService = TestBed.get(MachineServiceHistoryService);
    expect(service).toBeTruthy();
  });
});
