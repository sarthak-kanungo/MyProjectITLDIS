import { TestBed } from '@angular/core/testing';

import { PickListServiceService } from './pick-list-service.service';

describe('PickListServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PickListServiceService = TestBed.get(PickListServiceService);
    expect(service).toBeTruthy();
  });
});
