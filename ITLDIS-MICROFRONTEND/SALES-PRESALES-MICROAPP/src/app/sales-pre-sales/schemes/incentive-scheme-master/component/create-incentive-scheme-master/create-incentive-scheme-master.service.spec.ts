import { TestBed } from '@angular/core/testing';

import { CreateIncentiveSchemeMasterService } from './create-incentive-scheme-master.service';

describe('CreateIncentiveSchemeMasterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateIncentiveSchemeMasterService = TestBed.get(CreateIncentiveSchemeMasterService);
    expect(service).toBeTruthy();
  });
});
