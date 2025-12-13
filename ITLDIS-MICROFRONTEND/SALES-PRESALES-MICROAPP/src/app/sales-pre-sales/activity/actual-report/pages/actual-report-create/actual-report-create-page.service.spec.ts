import { TestBed } from '@angular/core/testing';

import { ActualReportCreatePageService } from './actual-report-create-page.service';

describe('ActualReportCreatePageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ActualReportCreatePageService = TestBed.get(ActualReportCreatePageService);
    expect(service).toBeTruthy();
  });
});
