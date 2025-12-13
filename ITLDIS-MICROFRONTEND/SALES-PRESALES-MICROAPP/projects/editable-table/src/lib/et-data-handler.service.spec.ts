import { TestBed } from '@angular/core/testing';
import { EtDataHandlerService } from './et-data-handler.service';


describe('EtDataHandlerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EtDataHandlerService = TestBed.get(EtDataHandlerService);
    expect(service).toBeTruthy();
  });
});
