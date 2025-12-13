import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparesSalesInvoiceItemDetailsComponent } from './spares-sales-invoice-item-details.component';

describe('SparesSalesInvoiceItemDetailsComponent', () => {
  let component: SparesSalesInvoiceItemDetailsComponent;
  let fixture: ComponentFixture<SparesSalesInvoiceItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparesSalesInvoiceItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparesSalesInvoiceItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
