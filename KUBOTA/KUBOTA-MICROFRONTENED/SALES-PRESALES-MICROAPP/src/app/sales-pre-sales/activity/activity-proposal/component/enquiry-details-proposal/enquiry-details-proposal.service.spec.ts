import { TestBed } from '@angular/core/testing';

import { EnquiryDetailsProposalService } from './enquiry-details-proposal.service';

describe('EnquiryDetailsProposalService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnquiryDetailsProposalService = TestBed.get(EnquiryDetailsProposalService);
    expect(service).toBeTruthy();
  });
});
