import { TestBed } from '@angular/core/testing';

import { EnquiryProductInterestedService } from './enquiry-product-interested.service';

describe('EnquiryProductInterestedService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnquiryProductInterestedService = TestBed.get(EnquiryProductInterestedService);
    expect(service).toBeTruthy();
  });
});
