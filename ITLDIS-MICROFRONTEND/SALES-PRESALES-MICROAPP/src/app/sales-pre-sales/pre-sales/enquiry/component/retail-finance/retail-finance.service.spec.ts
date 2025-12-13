import { TestBed } from '@angular/core/testing';

import { RetailFinanceService } from './retail-finance.service';

describe('RetailFinanceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RetailFinanceService = TestBed.get(RetailFinanceService);
    expect(service).toBeTruthy();
  });
});
