import { TestBed } from '@angular/core/testing';

import { PartRequisitionItemDetailService } from './part-requisition-item-detail.service';

describe('PartRequisitionItemDetailService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartRequisitionItemDetailService = TestBed.get(PartRequisitionItemDetailService);
    expect(service).toBeTruthy();
  });
});
