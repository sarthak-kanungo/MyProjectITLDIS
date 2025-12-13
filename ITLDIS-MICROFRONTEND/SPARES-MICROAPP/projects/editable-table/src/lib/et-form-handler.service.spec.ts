import { TestBed } from '@angular/core/testing';

import { EtFormHandlerService } from './et-form-handler.service';

describe('EtFormHandlerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EtFormHandlerService = TestBed.get(EtFormHandlerService);
    expect(service).toBeTruthy();
  });
});
