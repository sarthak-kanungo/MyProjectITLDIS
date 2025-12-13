import { TestBed } from '@angular/core/testing';

import { SchemesCommonService } from './schemes-common.service';

describe('SchemesCommonService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SchemesCommonService = TestBed.get(SchemesCommonService);
    expect(service).toBeTruthy();
  });
});
