import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetroFitmentCampaignSearchComponent } from './retro-fitment-campaign-search.component';

describe('RetroFitmentCampaignSearchComponent', () => {
  let component: RetroFitmentCampaignSearchComponent;
  let fixture: ComponentFixture<RetroFitmentCampaignSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetroFitmentCampaignSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetroFitmentCampaignSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
