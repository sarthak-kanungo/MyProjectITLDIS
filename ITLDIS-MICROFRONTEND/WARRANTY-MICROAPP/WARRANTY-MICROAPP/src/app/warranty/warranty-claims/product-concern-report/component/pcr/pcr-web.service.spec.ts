import { TestBed } from '@angular/core/testing';

import { PcrWebService } from './pcr-web.service';

describe('PcrWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PcrWebService = TestBed.get(PcrWebService);
    expect(service).toBeTruthy();
  });
});
