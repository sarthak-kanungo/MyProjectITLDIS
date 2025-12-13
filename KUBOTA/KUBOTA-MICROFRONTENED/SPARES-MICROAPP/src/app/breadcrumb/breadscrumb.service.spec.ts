import { TestBed } from '@angular/core/testing';

import { BreadscrumbService } from './breadscrumb.service';

describe('BreadscrumService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BreadscrumbService = TestBed.get(BreadscrumbService);
    expect(service).toBeTruthy();
  });
});
