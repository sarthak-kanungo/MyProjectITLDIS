import { TestBed } from '@angular/core/testing';

import { TransporterSearchWebService } from './transporter-search-web.service';

describe('TransporterSearchWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TransporterSearchWebService = TestBed.get(TransporterSearchWebService);
    expect(service).toBeTruthy();
  });
});
