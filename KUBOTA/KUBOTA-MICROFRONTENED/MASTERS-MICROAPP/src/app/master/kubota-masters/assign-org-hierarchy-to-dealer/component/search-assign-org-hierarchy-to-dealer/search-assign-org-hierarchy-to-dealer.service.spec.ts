import { TestBed } from '@angular/core/testing';
import { SearchAssignOrgHierarchyToDealerService } from './search-assign-org-hierarchy-to-dealer.service';

describe('SearchAssignOrgHierarchyToDealerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchAssignOrgHierarchyToDealerService = TestBed.get(SearchAssignOrgHierarchyToDealerService);
    expect(service).toBeTruthy();
  });
});
