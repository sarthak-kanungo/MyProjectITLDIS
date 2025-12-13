import { TestBed } from '@angular/core/testing';

import { InstallationMonitoringBoardService } from './installation-monitoring-board.service';

describe('InstallationMonitoringBoardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InstallationMonitoringBoardService = TestBed.get(InstallationMonitoringBoardService);
    expect(service).toBeTruthy();
  });
});
