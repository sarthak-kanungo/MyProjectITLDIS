import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryInstallationComponent } from './delivery-installation.component';

describe('DeliveryInstallationComponent', () => {
  let component: DeliveryInstallationComponent;
  let fixture: ComponentFixture<DeliveryInstallationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryInstallationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryInstallationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
