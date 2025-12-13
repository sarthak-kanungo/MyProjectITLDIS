import { TestBed } from '@angular/core/testing';

import { AddEnquiryFollowupContainerService } from './add-enquiry-followup-container.service';

describe('AddEnquiryFollowupContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddEnquiryFollowupContainerService = TestBed.get(AddEnquiryFollowupContainerService);
    expect(service).toBeTruthy();
  });
});
