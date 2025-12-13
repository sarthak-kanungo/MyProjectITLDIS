import { TestBed } from '@angular/core/testing';

import { CurrentMachineryDetailsV2WebService } from './current-machinery-details-v2-web.service';

describe('CurrentMachineryDetailsV2WebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CurrentMachineryDetailsV2WebService = TestBed.get(CurrentMachineryDetailsV2WebService);
    expect(service).toBeTruthy();
  });
});
