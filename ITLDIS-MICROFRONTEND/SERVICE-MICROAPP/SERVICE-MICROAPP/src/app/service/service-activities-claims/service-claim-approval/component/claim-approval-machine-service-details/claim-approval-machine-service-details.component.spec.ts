import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimApprovalMachineServiceDetailsComponent } from './claim-approval-machine-service-details.component';

describe('ClaimApprovalMachineServiceDetailsComponent', () => {
  let component: ClaimApprovalMachineServiceDetailsComponent;
  let fixture: ComponentFixture<ClaimApprovalMachineServiceDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClaimApprovalMachineServiceDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimApprovalMachineServiceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
