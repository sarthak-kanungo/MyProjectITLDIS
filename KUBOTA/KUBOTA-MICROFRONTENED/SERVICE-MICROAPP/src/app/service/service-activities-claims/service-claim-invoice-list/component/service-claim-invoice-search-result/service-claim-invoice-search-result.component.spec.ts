import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceClaimInvoiceSearchResultComponent } from './service-claim-invoice-search-result.component';

describe('ServiceClaimInvoiceSearchResultComponent', () => {
  let component: ServiceClaimInvoiceSearchResultComponent;
  let fixture: ComponentFixture<ServiceClaimInvoiceSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceClaimInvoiceSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceClaimInvoiceSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
