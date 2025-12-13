import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparesSalesInvoiceSearchComponent } from './spares-sales-invoice-search.component';

describe('SparesSalesInvoiceSearchComponent', () => {
  let component: SparesSalesInvoiceSearchComponent;
  let fixture: ComponentFixture<SparesSalesInvoiceSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparesSalesInvoiceSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparesSalesInvoiceSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
