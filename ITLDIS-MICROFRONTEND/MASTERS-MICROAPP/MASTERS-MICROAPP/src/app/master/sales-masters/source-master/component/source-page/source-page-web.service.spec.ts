import { TestBed } from '@angular/core/testing';

import { SourcePageWebService } from './source-page-web.service';

describe('SourcePageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SourcePageWebService = TestBed.get(SourcePageWebService);
    expect(service).toBeTruthy();
  });
});
