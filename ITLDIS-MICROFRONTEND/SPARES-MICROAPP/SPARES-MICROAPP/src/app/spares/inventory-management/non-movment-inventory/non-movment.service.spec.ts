import { TestBed } from '@angular/core/testing';

import { NonMovmentService } from './non-movment.service';

describe('NonMovmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NonMovmentService = TestBed.get(NonMovmentService);
    expect(service).toBeTruthy();
  });
});
