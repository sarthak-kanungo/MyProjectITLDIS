import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferReceiptItemDetailsComponent } from './branch-transfer-receipt-item-details.component';

describe('BranchTransferReceiptItemDetailsComponent', () => {
  let component: BranchTransferReceiptItemDetailsComponent;
  let fixture: ComponentFixture<BranchTransferReceiptItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferReceiptItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferReceiptItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
