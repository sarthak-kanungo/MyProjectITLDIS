import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVehicleRegistrationSearchResultComponent } from './update-vehicle-registration-search-result.component';

describe('UpdateVehicleRegistrationSearchResultComponent', () => {
  let component: UpdateVehicleRegistrationSearchResultComponent;
  let fixture: ComponentFixture<UpdateVehicleRegistrationSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateVehicleRegistrationSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateVehicleRegistrationSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
