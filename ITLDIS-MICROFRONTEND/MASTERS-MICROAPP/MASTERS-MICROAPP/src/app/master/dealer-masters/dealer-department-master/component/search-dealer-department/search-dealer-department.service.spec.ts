import { TestBed } from '@angular/core/testing';

import { SearchDealerDepartmentService } from './search-dealer-department.service';

describe('SearchDealerDepartmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchDealerDepartmentService = TestBed.get(SearchDealerDepartmentService);
    expect(service).toBeTruthy();
  });
});
