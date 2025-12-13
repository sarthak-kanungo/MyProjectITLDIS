import { TestBed } from '@angular/core/testing';

import { SparePaymentReceiptSearchPageService } from './spare-payment-receipt-search-page.service';

describe('SparePaymentReceiptSearchPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SparePaymentReceiptSearchPageService = TestBed.get(SparePaymentReceiptSearchPageService);
    expect(service).toBeTruthy();
  });
});
