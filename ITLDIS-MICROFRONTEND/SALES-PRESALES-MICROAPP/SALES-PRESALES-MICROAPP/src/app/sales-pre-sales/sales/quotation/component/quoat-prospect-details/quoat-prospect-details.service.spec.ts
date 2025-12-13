import { TestBed } from '@angular/core/testing';

import { QuoatProspectDetailsService } from './quoat-prospect-details.service';

describe('QuoatProspectDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuoatProspectDetailsService = TestBed.get(QuoatProspectDetailsService);
    expect(service).toBeTruthy();
  });
});
