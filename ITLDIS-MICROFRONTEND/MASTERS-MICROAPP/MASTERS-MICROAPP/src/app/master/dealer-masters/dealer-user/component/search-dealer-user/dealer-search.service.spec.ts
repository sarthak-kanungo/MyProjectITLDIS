import { TestBed } from '@angular/core/testing';

import { DealerSearchService } from './dealer-search.service';

describe('DealerSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DealerSearchService = TestBed.get(DealerSearchService);
    expect(service).toBeTruthy();
  });
});
