import { TestBed } from '@angular/core/testing';

import { EnquiryCreationContainerService } from './enquiry-creation-container.service';

describe('EnquiryCreationContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnquiryCreationContainerService = TestBed.get(EnquiryCreationContainerService);
    expect(service).toBeTruthy();
  });
});
