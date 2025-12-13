import { TestBed } from '@angular/core/testing';

import { QuotationCreateService } from './quotation-create.service';

describe('QuotationCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuotationCreateService = TestBed.get(QuotationCreateService);
    expect(service).toBeTruthy();
  });
});
