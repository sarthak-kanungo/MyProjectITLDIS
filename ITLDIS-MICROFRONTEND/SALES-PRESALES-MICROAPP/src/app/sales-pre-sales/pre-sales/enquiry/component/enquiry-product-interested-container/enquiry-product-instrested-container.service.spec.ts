import { TestBed } from '@angular/core/testing';

import { EnquiryProductInstrestedContainerService } from './enquiry-product-instrested-container.service';

describe('EnquiryProductInstrestedContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnquiryProductInstrestedContainerService = TestBed.get(EnquiryProductInstrestedContainerService);
    expect(service).toBeTruthy();
  });
});
