import { TestBed } from '@angular/core/testing';

import { BranchTransferRecieptService } from './branch-transfer-reciept.service';

describe('BranchTransferRecieptService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BranchTransferRecieptService = TestBed.get(BranchTransferRecieptService);
    expect(service).toBeTruthy();
  });
});
