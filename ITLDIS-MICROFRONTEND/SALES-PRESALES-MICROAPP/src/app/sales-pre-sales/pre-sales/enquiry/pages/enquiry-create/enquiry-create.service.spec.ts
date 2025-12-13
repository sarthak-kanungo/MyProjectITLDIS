import { TestBed } from '@angular/core/testing';

import { EnquiryCreateService } from './enquiry-create.service';

describe('EnquiryCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnquiryCreateService = TestBed.get(EnquiryCreateService);
    expect(service).toBeTruthy();
  });
});
