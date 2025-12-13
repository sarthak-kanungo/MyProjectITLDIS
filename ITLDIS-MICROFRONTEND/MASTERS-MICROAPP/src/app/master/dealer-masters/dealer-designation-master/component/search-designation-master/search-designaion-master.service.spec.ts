import { TestBed } from '@angular/core/testing';

import { SearchDesignaionMasterService } from './search-designaion-master.service';

describe('SearchDesignaionMasterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchDesignaionMasterService = TestBed.get(SearchDesignaionMasterService);
    expect(service).toBeTruthy();
  });
});
