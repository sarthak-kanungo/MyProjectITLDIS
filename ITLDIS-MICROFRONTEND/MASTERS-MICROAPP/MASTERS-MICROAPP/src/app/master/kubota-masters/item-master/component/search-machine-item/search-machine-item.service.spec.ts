import { TestBed } from '@angular/core/testing';

import { SearchMachineItemService } from './search-machine-item.service';

describe('SearchMachineItemService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchMachineItemService = TestBed.get(SearchMachineItemService);
    expect(service).toBeTruthy();
  });
});
