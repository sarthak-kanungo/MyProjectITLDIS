import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityClaimApprovalSearchComponent } from './activity-claim-approval-search.component';

describe('ActivityClaimApprovalSearchComponent', () => {
  let component: ActivityClaimApprovalSearchComponent;
  let fixture: ComponentFixture<ActivityClaimApprovalSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityClaimApprovalSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityClaimApprovalSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
