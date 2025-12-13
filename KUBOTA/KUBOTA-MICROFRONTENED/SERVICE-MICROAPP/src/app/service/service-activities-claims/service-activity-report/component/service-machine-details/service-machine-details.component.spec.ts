import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceMachineDetailsComponent } from './service-machine-details.component';

describe('ServiceMachineDetailsComponent', () => {
  let component: ServiceMachineDetailsComponent;
  let fixture: ComponentFixture<ServiceMachineDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceMachineDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceMachineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
