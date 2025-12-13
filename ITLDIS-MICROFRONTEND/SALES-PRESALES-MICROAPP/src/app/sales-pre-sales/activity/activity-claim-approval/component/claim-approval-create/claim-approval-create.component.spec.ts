import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimApprovalCreateComponent } from './claim-approval-create.component';

describe('ClaimApprovalCreateComponent', () => {
  let component: ClaimApprovalCreateComponent;
  let fixture: ComponentFixture<ClaimApprovalCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClaimApprovalCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimApprovalCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
