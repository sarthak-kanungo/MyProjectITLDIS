import { TestBed } from '@angular/core/testing';

import { CreateNewAssignOrgHierarchyToDealerService } from './create-new-assign-org-hierarchy-to-dealer.service';

describe('CreateNewAssignOrgHierarchyToDealerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateNewAssignOrgHierarchyToDealerService = TestBed.get(CreateNewAssignOrgHierarchyToDealerService);
    expect(service).toBeTruthy();
  });
});
