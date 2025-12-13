import { TestBed } from '@angular/core/testing';

import { ReInstallationCommonWebService } from './re-installation-common-web.service';

describe('ReInstallationCommonWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReInstallationCommonWebService = TestBed.get(ReInstallationCommonWebService);
    expect(service).toBeTruthy();
  });
});
