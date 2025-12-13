import { TestBed } from '@angular/core/testing';

import { SearchEnquiryV2WebService } from './search-enquiry-v2-web.service';

describe('SearchEnquiryV2WebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchEnquiryV2WebService = TestBed.get(SearchEnquiryV2WebService);
    expect(service).toBeTruthy();
  });
});
