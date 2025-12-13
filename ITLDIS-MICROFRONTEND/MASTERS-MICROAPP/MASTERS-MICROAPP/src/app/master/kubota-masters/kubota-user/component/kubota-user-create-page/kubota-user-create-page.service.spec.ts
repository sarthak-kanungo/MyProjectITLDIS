import { TestBed } from '@angular/core/testing';

import { itldisUserCreatePageService } from './itldis-user-create-page.service';

describe('itldisUserCreatePageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: itldisUserCreatePageService = TestBed.get(itldisUserCreatePageService);
    expect(service).toBeTruthy();
  });
});
