import { TestBed } from '@angular/core/testing';

import { ActivityReportCreateService } from './activity-report-create.service';

describe('ActivityReportCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityReportCreateService = TestBed.get(ActivityReportCreateService);
    expect(service).toBeTruthy();
  });
});
