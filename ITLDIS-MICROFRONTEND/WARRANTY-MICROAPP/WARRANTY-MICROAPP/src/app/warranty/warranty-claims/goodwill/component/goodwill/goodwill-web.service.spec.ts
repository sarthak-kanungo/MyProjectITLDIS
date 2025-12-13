import { TestBed } from '@angular/core/testing';

import { GoodwillWebService } from './goodwill-web.service';

describe('GoodwillWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GoodwillWebService = TestBed.get(GoodwillWebService);
    expect(service).toBeTruthy();
  });
});
