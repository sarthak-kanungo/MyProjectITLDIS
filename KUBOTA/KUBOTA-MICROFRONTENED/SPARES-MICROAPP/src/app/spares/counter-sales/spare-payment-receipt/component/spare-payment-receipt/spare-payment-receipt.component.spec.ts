import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparePaymentReceiptComponent } from './spare-payment-receipt.component';

describe('SparePaymentReceiptComponent', () => {
  let component: SparePaymentReceiptComponent;
  let fixture: ComponentFixture<SparePaymentReceiptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparePaymentReceiptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparePaymentReceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
