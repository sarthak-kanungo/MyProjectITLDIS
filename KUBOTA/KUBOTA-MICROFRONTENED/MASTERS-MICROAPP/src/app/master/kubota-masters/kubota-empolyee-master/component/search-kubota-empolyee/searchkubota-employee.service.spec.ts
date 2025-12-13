import { TestBed } from '@angular/core/testing';

import { SearchkubotaEmployeeService } from './searchkubota-employee.service';

describe('SearchkubotaEmployeeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchkubotaEmployeeService = TestBed.get(SearchkubotaEmployeeService);
    expect(service).toBeTruthy();
  });
});
