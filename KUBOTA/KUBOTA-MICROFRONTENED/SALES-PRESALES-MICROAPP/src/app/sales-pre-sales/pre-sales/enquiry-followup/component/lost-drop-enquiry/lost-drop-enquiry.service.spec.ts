import { TestBed } from '@angular/core/testing';

import { LostDropEnquiryService } from './lost-drop-enquiry.service';

describe('LostDropEnquiryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LostDropEnquiryService = TestBed.get(LostDropEnquiryService);
    expect(service).toBeTruthy();
  });
});
