import { TestBed } from '@angular/core/testing';

import { ActivityReportSearchTableService } from './activity-report-search-table.service';

describe('ActivityReportSearchTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityReportSearchTableService = TestBed.get(ActivityReportSearchTableService);
    expect(service).toBeTruthy();
  });
});
