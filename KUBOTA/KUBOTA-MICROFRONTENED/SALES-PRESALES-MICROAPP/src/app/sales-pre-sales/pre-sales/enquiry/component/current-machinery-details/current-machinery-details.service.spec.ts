import { TestBed } from '@angular/core/testing';

import { CurrentMachineryDetailsService } from './current-machinery-details.service';

describe('CurrentMachineryDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CurrentMachineryDetailsService = TestBed.get(CurrentMachineryDetailsService);
    expect(service).toBeTruthy();
  });
});
