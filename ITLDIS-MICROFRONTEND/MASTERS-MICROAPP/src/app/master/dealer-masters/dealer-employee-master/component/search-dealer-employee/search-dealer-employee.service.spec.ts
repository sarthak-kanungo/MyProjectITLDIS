import { TestBed } from '@angular/core/testing';

import { SearchDealerEmployeeService } from './search-dealer-employee.service';

describe('SearchDealerEmployeeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchDealerEmployeeService = TestBed.get(SearchDealerEmployeeService);
    expect(service).toBeTruthy();
  });
});
