import { TestBed } from '@angular/core/testing';

import { PartIssuePageApiService } from './part-issue-page-api.service';

describe('PartIssuePageApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartIssuePageApiService = TestBed.get(PartIssuePageApiService);
    expect(service).toBeTruthy();
  });
});
