import { TestBed } from '@angular/core/testing';

import { ImplementsAndAccessoriesService } from './implements-and-accessories.service';

describe('ImplementsAndAccessoriesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ImplementsAndAccessoriesService = TestBed.get(ImplementsAndAccessoriesService);
    expect(service).toBeTruthy();
  });
});
