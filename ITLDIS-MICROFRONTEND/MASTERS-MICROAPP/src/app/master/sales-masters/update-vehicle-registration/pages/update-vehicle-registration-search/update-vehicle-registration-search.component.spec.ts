import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVehicleRegistrationSearchComponent } from './update-vehicle-registration-search.component';

describe('UpdateVehicleRegistrationSearchComponent', () => {
  let component: UpdateVehicleRegistrationSearchComponent;
  let fixture: ComponentFixture<UpdateVehicleRegistrationSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateVehicleRegistrationSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateVehicleRegistrationSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
