import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferReceiptCreateComponent } from './branch-transfer-receipt-create.component';

describe('BranchTransferReceiptCreateComponent', () => {
  let component: BranchTransferReceiptCreateComponent;
  let fixture: ComponentFixture<BranchTransferReceiptCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferReceiptCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferReceiptCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
