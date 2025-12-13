import { TestBed } from '@angular/core/testing';

import { ActivityEnquiryReportService } from './activity-enquiry-report.service';

describe('ActivityEnquiryReportService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityEnquiryReportService = TestBed.get(ActivityEnquiryReportService);
    expect(service).toBeTruthy();
  });
});
