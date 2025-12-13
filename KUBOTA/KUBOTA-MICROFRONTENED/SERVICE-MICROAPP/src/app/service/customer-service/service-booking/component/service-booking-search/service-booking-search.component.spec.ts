import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceBookingSearchComponent } from './service-booking-search.component';

describe('ServiceBookingSearchComponent', () => {
  let component: ServiceBookingSearchComponent;
  let fixture: ComponentFixture<ServiceBookingSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceBookingSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceBookingSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
