import { TestBed } from '@angular/core/testing';

import { FunctionMasterPageService } from './function-master-page.service';

describe('FunctionMasterPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FunctionMasterPageService = TestBed.get(FunctionMasterPageService);
    expect(service).toBeTruthy();
  });
});
