import { TestBed } from '@angular/core/testing';

import { EditableTableService } from './editable-table.service';

describe('EditableTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EditableTableService = TestBed.get(EditableTableService);
    expect(service).toBeTruthy();
  });
});
