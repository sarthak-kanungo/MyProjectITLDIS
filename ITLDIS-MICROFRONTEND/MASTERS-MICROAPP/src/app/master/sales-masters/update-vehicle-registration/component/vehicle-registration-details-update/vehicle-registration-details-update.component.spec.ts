import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleRegistrationDetailsUpdateComponent } from './vehicle-registration-details-update.component';

describe('VehicleRegistrationDetailsUpdateComponent', () => {
  let component: VehicleRegistrationDetailsUpdateComponent;
  let fixture: ComponentFixture<VehicleRegistrationDetailsUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleRegistrationDetailsUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleRegistrationDetailsUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
