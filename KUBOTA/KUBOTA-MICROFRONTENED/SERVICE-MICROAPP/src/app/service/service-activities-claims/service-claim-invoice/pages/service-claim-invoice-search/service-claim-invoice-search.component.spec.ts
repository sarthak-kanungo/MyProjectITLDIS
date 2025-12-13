import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimInvoiceSearchComponent } from './service-claim-invoice-search.component';

describe('ServiceClaimInvoiceSearchComponent', () => {
  let component: ServiceClaimInvoiceSearchComponent;
  let fixture: ComponentFixture<ServiceClaimInvoiceSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimInvoiceSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimInvoiceSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
