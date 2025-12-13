import { TestBed } from '@angular/core/testing';

import { BtbtPageWebService } from './btbt-page-web.service';

describe('BtbtPageWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BtbtPageWebService = TestBed.get(BtbtPageWebService);
    expect(service).toBeTruthy();
  });
});
