import { TestBed } from '@angular/core/testing';

import { ActivityReportSearchTableContainerService } from './activity-report-search-table-container.service';

describe('ActivityReportSearchTableContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActivityReportSearchTableContainerService = TestBed.get(ActivityReportSearchTableContainerService);
    expect(service).toBeTruthy();
  });
});
