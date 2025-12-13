import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceInsuranceDetailsComponent } from './invoice-insurance-details.component';

describe('InvoiceInsuranceDetailsComponent', () => {
  let component: InvoiceInsuranceDetailsComponent;
  let fixture: ComponentFixture<InvoiceInsuranceDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceInsuranceDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceInsuranceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
