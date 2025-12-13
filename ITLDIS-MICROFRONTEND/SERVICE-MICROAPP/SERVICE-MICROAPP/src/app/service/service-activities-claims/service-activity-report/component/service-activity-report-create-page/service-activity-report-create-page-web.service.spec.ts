import { TestBed } from '@angular/core/testing';

import { ServiceActivityReportCreatePageWebService } from './service-activity-report-create-page-web.service';

describe('ServiceActivityReportCreatePageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ServiceActivityReportCreatePageWebService = TestBed.get(ServiceActivityReportCreatePageWebService);
    expect(service).toBeTruthy();
  });
});
