import { TestBed } from '@angular/core/testing';

import { SearchMtrListService } from './search-mtr-list.service';

describe('SearchMtrListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchMtrListService = TestBed.get(SearchMtrListService);
    expect(service).toBeTruthy();
  });
});
