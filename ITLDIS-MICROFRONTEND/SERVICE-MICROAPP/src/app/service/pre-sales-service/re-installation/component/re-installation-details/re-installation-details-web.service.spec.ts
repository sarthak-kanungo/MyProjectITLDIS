import { TestBed } from '@angular/core/testing';

import { ReInstallationDetailsWebService } from './re-installation-details-web.service';

describe('ReInstallationDetailsWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReInstallationDetailsWebService = TestBed.get(ReInstallationDetailsWebService);
    expect(service).toBeTruthy();
  });
});
