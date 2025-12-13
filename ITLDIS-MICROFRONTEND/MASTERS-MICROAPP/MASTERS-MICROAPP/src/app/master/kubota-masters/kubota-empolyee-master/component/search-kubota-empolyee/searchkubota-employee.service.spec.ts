import { TestBed } from '@angular/core/testing';

import { SearchitldisEmployeeService } from './searchitldis-employee.service';

describe('SearchitldisEmployeeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchitldisEmployeeService = TestBed.get(SearchitldisEmployeeService);
    expect(service).toBeTruthy();
  });
});
