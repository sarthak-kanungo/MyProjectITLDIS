import { TestBed } from '@angular/core/testing';

import { TransporterSearchPageWebService } from './transporter-search-page-web.service';

describe('TransporterSearchPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TransporterSearchPageWebService = TestBed.get(TransporterSearchPageWebService);
    expect(service).toBeTruthy();
  });
});
