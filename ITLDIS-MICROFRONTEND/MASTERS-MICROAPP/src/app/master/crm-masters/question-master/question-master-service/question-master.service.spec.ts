import { TestBed } from '@angular/core/testing';

import { QuestionMasterService } from './question-master.service';

describe('QuestionMasterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuestionMasterService = TestBed.get(QuestionMasterService);
    expect(service).toBeTruthy();
  });
});
