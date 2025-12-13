import { TestBed } from '@angular/core/testing';

import { SubActivityWebService } from './sub-activity-web.service';

describe('SubActivityWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SubActivityWebService = TestBed.get(SubActivityWebService);
    expect(service).toBeTruthy();
  });
});
