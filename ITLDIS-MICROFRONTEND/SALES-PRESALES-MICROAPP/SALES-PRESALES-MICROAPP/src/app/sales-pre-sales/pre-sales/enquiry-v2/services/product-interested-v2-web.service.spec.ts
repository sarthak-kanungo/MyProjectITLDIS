import { TestBed } from '@angular/core/testing';

import { ProductInterestedV2WebService } from './product-interested-v2-web.service';

describe('ProductInterestedV2WebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductInterestedV2WebService = TestBed.get(ProductInterestedV2WebService);
    expect(service).toBeTruthy();
  });
});
