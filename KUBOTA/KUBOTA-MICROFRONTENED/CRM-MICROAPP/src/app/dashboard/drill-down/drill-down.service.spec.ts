import { TestBed } from '@angular/core/testing';

import { DrillDownService } from './drill-down.service';

describe('DrillDownService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DrillDownService = TestBed.get(DrillDownService);
    expect(service).toBeTruthy();
  });
});
