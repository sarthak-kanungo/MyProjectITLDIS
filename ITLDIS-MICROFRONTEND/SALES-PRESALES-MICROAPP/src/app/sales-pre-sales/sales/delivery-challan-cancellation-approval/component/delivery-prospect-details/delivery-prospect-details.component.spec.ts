import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryProspectDetailsComponent } from './delivery-prospect-details.component';

describe('DeliveryProspectDetailsComponent', () => {
  let component: DeliveryProspectDetailsComponent;
  let fixture: ComponentFixture<DeliveryProspectDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryProspectDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryProspectDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
