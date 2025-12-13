import { TestBed } from '@angular/core/testing';

import { ExchangeInventorySaleService } from './exchange-inventory-sale.service';

describe('ExchangeInventorySaleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExchangeInventorySaleService = TestBed.get(ExchangeInventorySaleService);
    expect(service).toBeTruthy();
  });
});
