import { TestBed } from '@angular/core/testing';

import { DeliverableChecklistService } from './deliverable-checklist.service';

describe('DeliverableChecklistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeliverableChecklistService = TestBed.get(DeliverableChecklistService);
    expect(service).toBeTruthy();
  });
});
