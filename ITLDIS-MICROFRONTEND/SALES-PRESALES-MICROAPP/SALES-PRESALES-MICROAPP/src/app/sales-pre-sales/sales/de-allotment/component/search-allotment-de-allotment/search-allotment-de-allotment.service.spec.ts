import { TestBed } from '@angular/core/testing';

import { SearchAllotmentDeAllotmentService } from './search-allotment-de-allotment.service';

describe('SearchAllotmentDeAllotmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchAllotmentDeAllotmentService = TestBed.get(SearchAllotmentDeAllotmentService);
    expect(service).toBeTruthy();
  });
});
