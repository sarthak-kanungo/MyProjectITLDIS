import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityClaimApprovalCreateComponent } from './activity-claim-approval-create.component';

describe('ActivityClaimApprovalCreateComponent', () => {
  let component: ActivityClaimApprovalCreateComponent;
  let fixture: ComponentFixture<ActivityClaimApprovalCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityClaimApprovalCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityClaimApprovalCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
