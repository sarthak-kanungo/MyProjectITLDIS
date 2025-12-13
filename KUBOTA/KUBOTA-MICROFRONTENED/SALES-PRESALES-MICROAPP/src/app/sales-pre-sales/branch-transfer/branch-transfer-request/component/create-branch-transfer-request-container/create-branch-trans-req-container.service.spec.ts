import { TestBed } from '@angular/core/testing';

import { CreateBranchTransReqContainerService } from './create-branch-trans-req-container.service';

describe('CreateBranchTransReqContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateBranchTransReqContainerService = TestBed.get(CreateBranchTransReqContainerService);
    expect(service).toBeTruthy();
  });
});
