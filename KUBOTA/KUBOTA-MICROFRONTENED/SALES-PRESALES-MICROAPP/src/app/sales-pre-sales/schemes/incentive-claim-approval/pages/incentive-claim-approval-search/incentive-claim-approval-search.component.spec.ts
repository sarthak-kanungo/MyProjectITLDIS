import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncentiveClaimApprovalSearchComponent } from './incentive-claim-approval-search.component';

describe('IncentiveClaimApprovalSearchComponent', () => {
  let component: IncentiveClaimApprovalSearchComponent;
  let fixture: ComponentFixture<IncentiveClaimApprovalSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncentiveClaimApprovalSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncentiveClaimApprovalSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
