import { TestBed } from '@angular/core/testing';

import { ServiceActivityReportSearchWebService } from './service-activity-report-search-web.service';

describe('ServiceActivityReportSearchWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceActivityReportSearchWebService = TestBed.get(ServiceActivityReportSearchWebService);
    expect(service).toBeTruthy();
  });
});
