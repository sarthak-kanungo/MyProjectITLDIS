import { TestBed } from '@angular/core/testing';

import { LogSheetSearchPageWebService } from './log-sheet-search-page-web.service';

describe('LogSheetSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LogSheetSearchPageWebService = TestBed.get(LogSheetSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
