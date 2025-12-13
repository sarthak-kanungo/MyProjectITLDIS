import { TestBed } from '@angular/core/testing';

import { LogSheetSearchService } from './log-sheet-search.service';

describe('LogSheetSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LogSheetSearchService = TestBed.get(LogSheetSearchService);
    expect(service).toBeTruthy();
  });
});
