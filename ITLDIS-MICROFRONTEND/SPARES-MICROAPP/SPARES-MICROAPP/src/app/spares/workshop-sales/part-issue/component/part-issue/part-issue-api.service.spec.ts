import { TestBed } from '@angular/core/testing';

import { PartIssueApiService } from './part-issue-api.service';

describe('PartIssueApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartIssueApiService = TestBed.get(PartIssueApiService);
    expect(service).toBeTruthy();
  });
});
