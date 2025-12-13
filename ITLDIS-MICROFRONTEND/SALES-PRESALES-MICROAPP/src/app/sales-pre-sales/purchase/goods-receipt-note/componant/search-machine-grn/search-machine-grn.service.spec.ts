import { TestBed } from '@angular/core/testing';

import { SearchMachineGrnService } from './search-machine-grn.service';

describe('SearchMachineGrnService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchMachineGrnService = TestBed.get(SearchMachineGrnService);
    expect(service).toBeTruthy();
  });
});
