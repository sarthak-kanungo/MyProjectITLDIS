import { TestBed } from '@angular/core/testing';

import { ReInstallationPageWebService } from './re-installation-page-web.service';

describe('ReInstallationPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReInstallationPageWebService = TestBed.get(ReInstallationPageWebService);
    expect(service).toBeTruthy();
  });
});
