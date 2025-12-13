import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimApprovalSearchComponent } from './claim-approval-search.component';

describe('ClaimApprovalSearchComponent', () => {
  let component: ClaimApprovalSearchComponent;
  let fixture: ComponentFixture<ClaimApprovalSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClaimApprovalSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClaimApprovalSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
