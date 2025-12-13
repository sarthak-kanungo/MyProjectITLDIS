import { TestBed } from '@angular/core/testing';

import { MdComplaintsService } from './md-complaints.service';

describe('MdComplaintsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MdComplaintsService = TestBed.get(MdComplaintsService);
    expect(service).toBeTruthy();
  });
});
