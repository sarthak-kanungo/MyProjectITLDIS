import { TestBed } from '@angular/core/testing';

import { ProspectBackgroundV2WebService } from './prospect-background-v2-web.service';

describe('ProspectBackgroundV2WebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProspectBackgroundV2WebService = TestBed.get(ProspectBackgroundV2WebService);
    expect(service).toBeTruthy();
  });
});
