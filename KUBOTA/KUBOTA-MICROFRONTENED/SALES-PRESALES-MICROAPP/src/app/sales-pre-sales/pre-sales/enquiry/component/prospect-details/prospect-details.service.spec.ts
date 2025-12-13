import { TestBed } from '@angular/core/testing';

import { ProspectDetailsService } from './prospect-details.service';

describe('ProspectDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProspectDetailsService = TestBed.get(ProspectDetailsService);
    expect(service).toBeTruthy();
  });
});
