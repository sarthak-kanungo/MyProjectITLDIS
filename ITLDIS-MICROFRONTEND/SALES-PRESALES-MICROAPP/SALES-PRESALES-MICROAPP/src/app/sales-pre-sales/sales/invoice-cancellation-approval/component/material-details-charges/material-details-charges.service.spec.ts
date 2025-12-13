import { TestBed } from '@angular/core/testing';

import { MaterialDetailsChargesService } from './material-details-charges.service';

describe('MaterialDetailsChargesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MaterialDetailsChargesService = TestBed.get(MaterialDetailsChargesService);
    expect(service).toBeTruthy();
  });
});
