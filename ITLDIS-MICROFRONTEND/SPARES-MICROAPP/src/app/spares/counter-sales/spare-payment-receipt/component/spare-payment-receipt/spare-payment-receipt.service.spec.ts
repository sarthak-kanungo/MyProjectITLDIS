import { TestBed } from '@angular/core/testing';

import { SparePaymentReceiptService } from './spare-payment-receipt.service';

describe('SparePaymentReceiptService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SparePaymentReceiptService = TestBed.get(SparePaymentReceiptService);
    expect(service).toBeTruthy();
  });
});
