import { TestBed } from '@angular/core/testing';

import { CustomerSatisfactionService } from './customer-satisfaction.service';

describe('CustomerSatisfactionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomerSatisfactionService = TestBed.get(CustomerSatisfactionService);
    expect(service).toBeTruthy();
  });
});
