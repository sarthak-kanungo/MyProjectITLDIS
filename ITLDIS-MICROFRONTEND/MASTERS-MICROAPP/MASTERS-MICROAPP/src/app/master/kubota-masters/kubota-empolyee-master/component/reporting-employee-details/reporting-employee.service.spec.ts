import { TestBed } from '@angular/core/testing';

import { ReportingEmployeeService } from './reporting-employee.service';

describe('ReportingEmployeeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReportingEmployeeService = TestBed.get(ReportingEmployeeService);
    expect(service).toBeTruthy();
  });
});
