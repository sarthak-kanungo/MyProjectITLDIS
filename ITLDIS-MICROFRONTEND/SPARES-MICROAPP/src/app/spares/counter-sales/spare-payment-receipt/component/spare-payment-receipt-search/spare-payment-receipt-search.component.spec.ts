import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparePaymentReceiptSearchComponent } from './spare-payment-receipt-search.component';

describe('SparePaymentReceiptSearchComponent', () => {
  let component: SparePaymentReceiptSearchComponent;
  let fixture: ComponentFixture<SparePaymentReceiptSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparePaymentReceiptSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparePaymentReceiptSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
