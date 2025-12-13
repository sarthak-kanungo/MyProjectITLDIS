import { TestBed } from '@angular/core/testing';

import { OrghierarchyModalService } from './orghierarchy-modal.service';

describe('OrghierarchyModalService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrghierarchyModalService = TestBed.get(OrghierarchyModalService);
    expect(service).toBeTruthy();
  });
});
