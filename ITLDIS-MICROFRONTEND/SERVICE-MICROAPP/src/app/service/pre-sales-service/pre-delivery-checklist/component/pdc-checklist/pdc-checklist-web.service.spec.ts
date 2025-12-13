import { TestBed } from '@angular/core/testing';

import { PdcChecklistWebService } from './pdc-checklist-web.service';

describe('PdcChecklistWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PdcChecklistWebService = TestBed.get(PdcChecklistWebService);
    expect(service).toBeTruthy();
  });
});
