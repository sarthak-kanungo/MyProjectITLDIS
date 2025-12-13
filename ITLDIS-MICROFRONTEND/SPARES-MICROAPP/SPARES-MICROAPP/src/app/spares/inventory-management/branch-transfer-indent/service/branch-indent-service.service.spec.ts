import { TestBed } from '@angular/core/testing';

import { BranchIndentServiceService } from './branch-indent-service.service';

describe('BranchIndentServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BranchIndentServiceService = TestBed.get(BranchIndentServiceService);
    expect(service).toBeTruthy();
  });
});
