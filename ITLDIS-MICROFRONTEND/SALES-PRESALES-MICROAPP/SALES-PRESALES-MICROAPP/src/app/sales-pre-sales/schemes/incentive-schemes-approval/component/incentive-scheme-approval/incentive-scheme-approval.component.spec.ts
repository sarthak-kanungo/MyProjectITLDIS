import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncentiveSchemeApprovalComponent } from './incentive-scheme-approval.component';

describe('IncentiveSchemeApprovalComponent', () => {
  let component: IncentiveSchemeApprovalComponent;
  let fixture: ComponentFixture<IncentiveSchemeApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncentiveSchemeApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncentiveSchemeApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
