import { TestBed } from '@angular/core/testing';

import { ActivityReportJobCardDetailsWebService } from './activity-report-job-card-details-web.service';

describe('ActivityReportJobCardDetailsWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityReportJobCardDetailsWebService = TestBed.get(ActivityReportJobCardDetailsWebService);
    expect(service).toBeTruthy();
  });
});
