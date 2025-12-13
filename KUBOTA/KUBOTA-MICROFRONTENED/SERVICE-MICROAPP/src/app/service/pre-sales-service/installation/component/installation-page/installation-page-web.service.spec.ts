import { TestBed } from '@angular/core/testing';

import { InstallationPageWebService } from './installation-page-web.service';

describe('InstallationPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InstallationPageWebService = TestBed.get(InstallationPageWebService);
    expect(service).toBeTruthy();
  });
});
