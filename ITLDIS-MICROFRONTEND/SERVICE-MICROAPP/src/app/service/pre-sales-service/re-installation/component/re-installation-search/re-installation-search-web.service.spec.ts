import { TestBed } from '@angular/core/testing';

import { ReInstallationSearchWebService } from './re-installation-search-web.service';

describe('ReInstallationSearchWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ReInstallationSearchWebService = TestBed.get(ReInstallationSearchWebService);
    expect(service).toBeTruthy();
  });
});
