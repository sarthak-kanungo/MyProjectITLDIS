import { TestBed } from '@angular/core/testing';

import { ComplaintOrQueryResolutionSearchPageService } from './complaint-or-query-resolution-search-page.service';

describe('ComplaintOrQueryResolutionSearchPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ComplaintOrQueryResolutionSearchPageService = TestBed.get(ComplaintOrQueryResolutionSearchPageService);
    expect(service).toBeTruthy();
  });
});
