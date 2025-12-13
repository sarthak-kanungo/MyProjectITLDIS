import { TestBed } from '@angular/core/testing';

import { ItemDetailsWebService } from './item-details-web.service';

describe('ItemDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ItemDetailsWebService = TestBed.get(ItemDetailsWebService);
    expect(service).toBeTruthy();
  });
});
