import { TestBed } from '@angular/core/testing';

import { SearchAssignUserToBranchService } from './search-assign-user-to-branch.service';

describe('SearchAssignUserToBranchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchAssignUserToBranchService = TestBed.get(SearchAssignUserToBranchService);
    expect(service).toBeTruthy();
  });
});
