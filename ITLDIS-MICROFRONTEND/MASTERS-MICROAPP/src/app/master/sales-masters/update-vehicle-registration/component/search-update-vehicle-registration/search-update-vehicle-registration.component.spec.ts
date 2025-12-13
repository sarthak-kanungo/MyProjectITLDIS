import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchUpdateVehicleRegistrationComponent } from './search-update-vehicle-registration.component';

describe('SearchUpdateVehicleRegistrationComponent', () => {
  let component: SearchUpdateVehicleRegistrationComponent;
  let fixture: ComponentFixture<SearchUpdateVehicleRegistrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchUpdateVehicleRegistrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchUpdateVehicleRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
