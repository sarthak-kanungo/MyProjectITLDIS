import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceInsuranceDetailsContainerComponent } from './invoice-insurance-details-container.component';

describe('InvoiceInsuranceDetailsContainerComponent', () => {
  let component: InvoiceInsuranceDetailsContainerComponent;
  let fixture: ComponentFixture<InvoiceInsuranceDetailsContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceInsuranceDetailsContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceInsuranceDetailsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
