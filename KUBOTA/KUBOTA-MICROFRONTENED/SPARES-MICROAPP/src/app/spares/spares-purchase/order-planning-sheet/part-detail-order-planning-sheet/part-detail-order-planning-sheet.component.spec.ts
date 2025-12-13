import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartDetailOrderPlanningSheetComponent } from './part-detail-order-planning-sheet.component';

describe('PartDetailOrderPlanningSheetComponent', () => {
  let component: PartDetailOrderPlanningSheetComponent;
  let fixture: ComponentFixture<PartDetailOrderPlanningSheetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartDetailOrderPlanningSheetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartDetailOrderPlanningSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
