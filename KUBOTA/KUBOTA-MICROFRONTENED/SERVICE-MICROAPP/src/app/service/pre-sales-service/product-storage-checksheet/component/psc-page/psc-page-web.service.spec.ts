import { TestBed } from '@angular/core/testing';

import { PscPageWebService } from './psc-page-web.service';

describe('PscPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PscPageWebService = TestBed.get(PscPageWebService);
    expect(service).toBeTruthy();
  });
});
