import { TestBed } from '@angular/core/testing';

import { DealerTerritoryMappingService } from './dealer-territory-mapping.service';

describe('DealerTerritoryMappingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DealerTerritoryMappingService = TestBed.get(DealerTerritoryMappingService);
    expect(service).toBeTruthy();
  });
});
