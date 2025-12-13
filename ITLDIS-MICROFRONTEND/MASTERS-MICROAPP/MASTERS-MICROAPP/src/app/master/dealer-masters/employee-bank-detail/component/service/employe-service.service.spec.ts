import { TestBed } from '@angular/core/testing';

import { EmployeServiceService } from './employe-service.service';

describe('EmployeServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmployeServiceService = TestBed.get(EmployeServiceService);
    expect(service).toBeTruthy();
  });
});
