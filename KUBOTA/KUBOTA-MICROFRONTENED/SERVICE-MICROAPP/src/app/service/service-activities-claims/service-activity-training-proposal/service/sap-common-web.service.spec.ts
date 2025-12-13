import { TestBed } from '@angular/core/testing';

import { SapCommonWebService } from './sap-common-web.service';

describe('SapCommonWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SapCommonWebService = TestBed.get(SapCommonWebService);
    expect(service).toBeTruthy();
  });
});
