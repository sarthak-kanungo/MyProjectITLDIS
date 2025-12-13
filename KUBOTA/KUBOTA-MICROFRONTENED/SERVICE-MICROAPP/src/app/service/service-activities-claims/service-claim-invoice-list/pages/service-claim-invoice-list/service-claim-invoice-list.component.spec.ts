import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimInvoiceListComponent } from './service-claim-invoice-list.component';

describe('ServiceClaimInvoiceListComponent', () => {
  let component: ServiceClaimInvoiceListComponent;
  let fixture: ComponentFixture<ServiceClaimInvoiceListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimInvoiceListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimInvoiceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
