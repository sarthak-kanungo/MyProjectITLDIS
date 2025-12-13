import { TestBed } from '@angular/core/testing';

import { NgswSearchTableService } from './ngsw-search-table.service';

describe('NgswSearchTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NgswSearchTableService = TestBed.get(NgswSearchTableService);
    expect(service).toBeTruthy();
  });
});
