import { TestBed } from '@angular/core/testing';

import { ServiceMonitoringBoardService } from './service-monitoring-board.service';

describe('ServiceMonitoringBoardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceMonitoringBoardService = TestBed.get(ServiceMonitoringBoardService);
    expect(service).toBeTruthy();
  });
});
