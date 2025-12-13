import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVehicleRegistrationCreateComponent } from './update-vehicle-registration-create.component';

describe('UpdateVehicleRegistrationCreateComponent', () => {
  let component: UpdateVehicleRegistrationCreateComponent;
  let fixture: ComponentFixture<UpdateVehicleRegistrationCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateVehicleRegistrationCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateVehicleRegistrationCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
