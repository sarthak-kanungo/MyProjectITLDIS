import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparesSalesInvoiceComponent } from './spares-sales-invoice.component';

describe('SparesSalesInvoiceComponent', () => {
  let component: SparesSalesInvoiceComponent;
  let fixture: ComponentFixture<SparesSalesInvoiceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparesSalesInvoiceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparesSalesInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
