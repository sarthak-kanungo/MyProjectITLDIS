import { TestBed } from '@angular/core/testing';

import { SearchitldisUserService } from './search-itldis-user.service';

describe('SearchitldisUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchitldisUserService = TestBed.get(SearchitldisUserService);
    expect(service).toBeTruthy();
  });
});
