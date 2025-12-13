import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryInsuranceDetailsComponent } from './delivery-insurance-details.component';

describe('DeliveryInsuranceDetailsComponent', () => {
  let component: DeliveryInsuranceDetailsComponent;
  let fixture: ComponentFixture<DeliveryInsuranceDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryInsuranceDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryInsuranceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
