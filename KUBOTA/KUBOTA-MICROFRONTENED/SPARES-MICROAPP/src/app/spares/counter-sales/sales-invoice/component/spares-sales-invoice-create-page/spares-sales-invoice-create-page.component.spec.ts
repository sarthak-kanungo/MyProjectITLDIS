import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SparesSalesInvoiceCreatePageComponent } from './spares-sales-invoice-create-page.component';

describe('SparesSalesInvoiceCreatePageComponent', () => {
  let component: SparesSalesInvoiceCreatePageComponent;
  let fixture: ComponentFixture<SparesSalesInvoiceCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SparesSalesInvoiceCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SparesSalesInvoiceCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
