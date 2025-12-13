import { TestBed } from '@angular/core/testing';

import { SalePopUpService } from './sale-pop-up.service';

describe('SalePopUpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalePopUpService = TestBed.get(SalePopUpService);
    expect(service).toBeTruthy();
  });
});
