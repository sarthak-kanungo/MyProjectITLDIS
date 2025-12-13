import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketingActivityClaimApprovalComponent } from './marketing-activity-claim-approval.component';

describe('MarketingActivityClaimApprovalComponent', () => {
  let component: MarketingActivityClaimApprovalComponent;
  let fixture: ComponentFixture<MarketingActivityClaimApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketingActivityClaimApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketingActivityClaimApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
