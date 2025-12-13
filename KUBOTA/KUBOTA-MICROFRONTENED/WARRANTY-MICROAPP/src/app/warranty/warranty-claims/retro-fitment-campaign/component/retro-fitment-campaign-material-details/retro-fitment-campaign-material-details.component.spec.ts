import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetroFitmentCampaignMaterialDetailsComponent } from './retro-fitment-campaign-material-details.component';

describe('RetroFitmentCampaignMaterialDetailsComponent', () => {
  let component: RetroFitmentCampaignMaterialDetailsComponent;
  let fixture: ComponentFixture<RetroFitmentCampaignMaterialDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetroFitmentCampaignMaterialDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetroFitmentCampaignMaterialDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
