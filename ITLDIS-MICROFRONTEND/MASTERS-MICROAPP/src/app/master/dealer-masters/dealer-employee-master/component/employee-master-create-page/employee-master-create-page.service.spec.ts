import { TestBed } from '@angular/core/testing';

import { EmployeeMasterCreatePageService } from './employee-master-create-page.service';

describe('EmployeeMasterCreatePageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmployeeMasterCreatePageService = TestBed.get(EmployeeMasterCreatePageService);
    expect(service).toBeTruthy();
  });
});
