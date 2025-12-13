import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketingActivityApprovalComponent } from './marketing-activity-approval.component';

describe('MarketingActivityApprovalComponent', () => {
  let component: MarketingActivityApprovalComponent;
  let fixture: ComponentFixture<MarketingActivityApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketingActivityApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketingActivityApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
