import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryMovementReportComponent } from './inventory-movement-report.component';

describe('InventoryMovementReportComponent', () => {
  let component: InventoryMovementReportComponent;
  let fixture: ComponentFixture<InventoryMovementReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventoryMovementReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryMovementReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
