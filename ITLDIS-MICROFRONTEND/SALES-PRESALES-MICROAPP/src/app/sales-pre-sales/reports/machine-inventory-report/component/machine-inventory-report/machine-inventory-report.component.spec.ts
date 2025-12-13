import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineInventoryReportComponent } from './machine-inventory-report.component';

describe('MachineInventoryReportComponent', () => {
  let component: MachineInventoryReportComponent;
  let fixture: ComponentFixture<MachineInventoryReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineInventoryReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineInventoryReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
