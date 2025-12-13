import { TestBed } from '@angular/core/testing';

import { RetroFitmentCampaignMaterialDetailsWebService } from './retro-fitment-campaign-material-details-web.service';

describe('RetroFitmentCampaignMaterialDetailsWebService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RetroFitmentCampaignMaterialDetailsWebService = TestBed.get(RetroFitmentCampaignMaterialDetailsWebService);
    expect(service).toBeTruthy();
  });
});
