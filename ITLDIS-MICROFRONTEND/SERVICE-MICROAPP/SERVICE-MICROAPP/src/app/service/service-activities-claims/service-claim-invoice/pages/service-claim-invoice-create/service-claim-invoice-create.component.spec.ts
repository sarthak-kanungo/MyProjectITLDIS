import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimInvoiceCreateComponent } from './service-claim-invoice-create.component';

describe('ServiceClaimInvoiceCreateComponent', () => {
  let component: ServiceClaimInvoiceCreateComponent;
  let fixture: ComponentFixture<ServiceClaimInvoiceCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimInvoiceCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimInvoiceCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
