import { TestBed } from '@angular/core/testing';

import { RetailFinanceContainerService } from './retail-finance-container.service';

describe('RetailFinanceContainerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RetailFinanceContainerService = TestBed.get(RetailFinanceContainerService);
    expect(service).toBeTruthy();
  });
});
