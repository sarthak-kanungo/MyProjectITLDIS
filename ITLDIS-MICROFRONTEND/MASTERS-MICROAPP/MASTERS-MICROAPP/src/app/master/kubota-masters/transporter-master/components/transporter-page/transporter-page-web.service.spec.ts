import { TestBed } from '@angular/core/testing';

import { TransporterPageWebService } from './transporter-page-web.service';

describe('TransporterPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TransporterPageWebService = TestBed.get(TransporterPageWebService);
    expect(service).toBeTruthy();
  });
});
