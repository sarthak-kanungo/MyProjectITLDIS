import { TestBed } from '@angular/core/testing';

import { EnquiryCreateCommonService } from './enquiry-create-common.service';

describe('EnquiryCreateCommonService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnquiryCreateCommonService = TestBed.get(EnquiryCreateCommonService);
    expect(service).toBeTruthy();
  });
});
