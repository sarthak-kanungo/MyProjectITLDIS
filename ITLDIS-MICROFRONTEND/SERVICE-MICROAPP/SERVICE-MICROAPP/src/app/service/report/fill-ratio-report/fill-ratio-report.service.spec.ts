import { TestBed } from '@angular/core/testing';

import { FillRatioReportService } from './fill-ratio-report.service';

describe('FillRatioReportService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FillRatioReportService = TestBed.get(FillRatioReportService);
    expect(service).toBeTruthy();
  });
});
