import { TestBed } from '@angular/core/testing';

import { SearchDealerService } from './search-dealer.service';

describe('SearchDealerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchDealerService = TestBed.get(SearchDealerService);
    expect(service).toBeTruthy();
  });
});
