import { TestBed } from '@angular/core/testing';

import { ReInstallationMachineDetailsWebService } from './re-installation-machine-details-web.service';

describe('ReInstallationMachineDetailsWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReInstallationMachineDetailsWebService = TestBed.get(ReInstallationMachineDetailsWebService);
    expect(service).toBeTruthy();
  });
});
