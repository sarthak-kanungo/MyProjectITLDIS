import { TestBed } from '@angular/core/testing';

import { ChannelFinanceLimitUploadService } from './channel-finance-limit-upload.service';

describe('ChannelFinanceLimitUploadService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ChannelFinanceLimitUploadService = TestBed.get(ChannelFinanceLimitUploadService);
    expect(service).toBeTruthy();
  });
});
