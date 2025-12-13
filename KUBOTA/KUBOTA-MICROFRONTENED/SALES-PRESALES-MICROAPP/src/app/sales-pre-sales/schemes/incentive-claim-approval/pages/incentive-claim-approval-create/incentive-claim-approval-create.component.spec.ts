import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncentiveClaimApprovalCreateComponent } from './incentive-claim-approval-create.component';

describe('IncentiveClaimApprovalCreateComponent', () => {
  let component: IncentiveClaimApprovalCreateComponent;
  let fixture: ComponentFixture<IncentiveClaimApprovalCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncentiveClaimApprovalCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncentiveClaimApprovalCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
