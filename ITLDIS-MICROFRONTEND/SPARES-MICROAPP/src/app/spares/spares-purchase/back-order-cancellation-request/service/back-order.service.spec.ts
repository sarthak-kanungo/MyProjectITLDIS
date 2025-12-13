import { TestBed } from '@angular/core/testing';

import { BackOrderService } from './back-order.service';

describe('BackOrderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BackOrderService = TestBed.get(BackOrderService);
    expect(service).toBeTruthy();
  });
});
