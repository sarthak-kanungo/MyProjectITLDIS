import { TestBed } from '@angular/core/testing';
import { WcrPartsStatusService } from './wcr-parts-status.service';



describe('WcrPartsStatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WcrPartsStatusService = TestBed.get(WcrPartsStatusService);
    expect(service).toBeTruthy();
  });
});
