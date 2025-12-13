import { TestBed } from '@angular/core/testing';

import { InstallationCommonWebService } from './installation-common-web.service';

describe('InstallationCommonWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InstallationCommonWebService = TestBed.get(InstallationCommonWebService);
    expect(service).toBeTruthy();
  });
});
