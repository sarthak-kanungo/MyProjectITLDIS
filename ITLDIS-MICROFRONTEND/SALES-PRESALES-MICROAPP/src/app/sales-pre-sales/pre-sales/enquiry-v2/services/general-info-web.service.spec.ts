import { TestBed } from '@angular/core/testing';

import { GeneralInfoWebService } from './general-info-web.service';

describe('GeneralInfoWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GeneralInfoWebService = TestBed.get(GeneralInfoWebService);
    expect(service).toBeTruthy();
  });
});
