import { TestBed } from '@angular/core/testing';

import { SearchQuotationContainerService } from './search-quotation-container.service';

describe('SearchQuotationContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchQuotationContainerService = TestBed.get(SearchQuotationContainerService);
    expect(service).toBeTruthy();
  });
});
