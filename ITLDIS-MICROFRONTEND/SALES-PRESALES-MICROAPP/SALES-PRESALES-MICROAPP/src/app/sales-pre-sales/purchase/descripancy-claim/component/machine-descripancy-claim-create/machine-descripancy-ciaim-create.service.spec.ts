import { TestBed } from '@angular/core/testing';

import { MachineDescripancyCiaimCreateService } from './machine-descripancy-ciaim-create.service';

describe('MachineDescripancyCiaimCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MachineDescripancyCiaimCreateService = TestBed.get(MachineDescripancyCiaimCreateService);
    expect(service).toBeTruthy();
  });
});
