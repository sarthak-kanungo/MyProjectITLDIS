import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimApprovalDealerInfoComponent } from './claim-approval-dealer-info.component';

describe('ClaimApprovalDealerInfoComponent', () => {
  let component: ClaimApprovalDealerInfoComponent;
  let fixture: ComponentFixture<ClaimApprovalDealerInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClaimApprovalDealerInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimApprovalDealerInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
