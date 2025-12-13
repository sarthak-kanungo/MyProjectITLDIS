import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimInvoiceDetailsComponent } from './service-claim-invoice-details.component';

describe('ServiceClaimInvoiceDetailsComponent', () => {
  let component: ServiceClaimInvoiceDetailsComponent;
  let fixture: ComponentFixture<ServiceClaimInvoiceDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimInvoiceDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimInvoiceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
