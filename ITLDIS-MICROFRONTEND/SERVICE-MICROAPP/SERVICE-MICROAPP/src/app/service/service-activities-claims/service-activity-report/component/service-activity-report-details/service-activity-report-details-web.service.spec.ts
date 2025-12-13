import { TestBed } from '@angular/core/testing';

import { ServiceActivityReportDetailsWebService } from './service-activity-report-details-web.service';

describe('ServiceActivityReportDetailsWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceActivityReportDetailsWebService = TestBed.get(ServiceActivityReportDetailsWebService);
    expect(service).toBeTruthy();
  });
});
