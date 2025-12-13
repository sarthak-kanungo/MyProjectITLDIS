import { TestBed } from '@angular/core/testing';

import { AddDesignationLevelService } from './add-designation-level.service';

describe('AddDesignationLevelService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddDesignationLevelService = TestBed.get(AddDesignationLevelService);
    expect(service).toBeTruthy();
  });
});
