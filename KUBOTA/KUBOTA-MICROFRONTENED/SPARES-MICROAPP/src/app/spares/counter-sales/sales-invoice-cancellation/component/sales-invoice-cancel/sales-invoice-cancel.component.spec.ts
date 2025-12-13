import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesInvoiceCancelComponent } from './sales-invoice-cancel.component';

describe('SalesInvoiceCancelComponent', () => {
  let component: SalesInvoiceCancelComponent;
  let fixture: ComponentFixture<SalesInvoiceCancelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalesInvoiceCancelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesInvoiceCancelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
