import { TestBed } from '@angular/core/testing';

import { TransporterCommonWebService } from './transporter-common-web.service';

describe('TransporterCommonWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TransporterCommonWebService = TestBed.get(TransporterCommonWebService);
    expect(service).toBeTruthy();
  });
});
