import { TestBed } from '@angular/core/testing';

import { LogSheetWebService } from './log-sheet-web.service';

describe('LogSheetWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LogSheetWebService = TestBed.get(LogSheetWebService);
    expect(service).toBeTruthy();
  });
});
