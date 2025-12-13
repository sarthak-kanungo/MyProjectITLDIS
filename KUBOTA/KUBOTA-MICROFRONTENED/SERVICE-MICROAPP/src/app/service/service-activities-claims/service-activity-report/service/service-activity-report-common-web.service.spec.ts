import { TestBed } from '@angular/core/testing';

import { ServiceActivityReportCommonWebService } from './service-activity-report-common-web.service';

describe('ServiceActivityReportCommonWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceActivityReportCommonWebService = TestBed.get(ServiceActivityReportCommonWebService);
    expect(service).toBeTruthy();
  });
});
