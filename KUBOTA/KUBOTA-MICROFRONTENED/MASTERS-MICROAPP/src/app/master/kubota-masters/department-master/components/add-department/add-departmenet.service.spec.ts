import { TestBed } from '@angular/core/testing';

import { AddDepartmenetService } from './add-departmenet.service';

describe('AddDepartmenetService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddDepartmenetService = TestBed.get(AddDepartmenetService);
    expect(service).toBeTruthy();
  });
});
