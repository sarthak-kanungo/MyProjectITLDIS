import { TestBed } from '@angular/core/testing';

import { InsuranceDetailAdaptorService } from './insurance-detail-adaptor.service';

describe('InsuranceDetailAdaptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InsuranceDetailAdaptorService = TestBed.get(InsuranceDetailAdaptorService);
    expect(service).toBeTruthy();
  });
});
