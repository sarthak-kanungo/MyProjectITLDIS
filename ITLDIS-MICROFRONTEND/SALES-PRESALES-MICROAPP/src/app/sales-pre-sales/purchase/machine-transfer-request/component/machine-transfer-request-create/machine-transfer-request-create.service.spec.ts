import { TestBed } from '@angular/core/testing';

import { MachineTransferRequestCreateService } from './machine-transfer-request-create.service';

describe('MachineTransferRequestCreateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MachineTransferRequestCreateService = TestBed.get(MachineTransferRequestCreateService);
    expect(service).toBeTruthy();
  });
});
