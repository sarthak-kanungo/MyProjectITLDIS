import { TestBed } from '@angular/core/testing';

import { EnquiryDetailsService } from './enquiry-details.service';

describe('EnquiryDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnquiryDetailsService = TestBed.get(EnquiryDetailsService);
    expect(service).toBeTruthy();
  });
});
