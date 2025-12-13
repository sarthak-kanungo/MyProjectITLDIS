import { TestBed } from '@angular/core/testing';

import { MaterialDetailApiService } from './material-detail-api.service';

describe('MaterialDetailApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MaterialDetailApiService = TestBed.get(MaterialDetailApiService);
    expect(service).toBeTruthy();
  });
});
