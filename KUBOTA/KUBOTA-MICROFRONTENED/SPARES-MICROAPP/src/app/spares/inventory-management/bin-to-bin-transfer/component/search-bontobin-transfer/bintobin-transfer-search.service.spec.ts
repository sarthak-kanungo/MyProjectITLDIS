import { TestBed } from '@angular/core/testing';

import { BintobinTransferSearchService } from './bintobin-transfer-search.service';

describe('BintobinTransferSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BintobinTransferSearchService = TestBed.get(BintobinTransferSearchService);
    expect(service).toBeTruthy();
  });
});
