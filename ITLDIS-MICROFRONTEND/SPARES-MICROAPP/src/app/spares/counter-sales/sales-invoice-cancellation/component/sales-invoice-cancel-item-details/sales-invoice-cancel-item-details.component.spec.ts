import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesInvoiceCancelItemDetailsComponent } from './sales-invoice-cancel-item-details.component';

describe('SalesInvoiceCancelItemDetailsComponent', () => {
  let component: SalesInvoiceCancelItemDetailsComponent;
  let fixture: ComponentFixture<SalesInvoiceCancelItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalesInvoiceCancelItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesInvoiceCancelItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
