import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceCancellationApprovalSearchComponent } from './invoice-cancellation-approval-search.component';

describe('InvoiceCancellationApprovalSearchComponent', () => {
  let component: InvoiceCancellationApprovalSearchComponent;
  let fixture: ComponentFixture<InvoiceCancellationApprovalSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceCancellationApprovalSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceCancellationApprovalSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
