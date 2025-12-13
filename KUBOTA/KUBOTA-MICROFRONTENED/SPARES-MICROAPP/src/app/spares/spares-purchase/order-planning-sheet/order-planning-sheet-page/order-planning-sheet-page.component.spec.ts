import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderPlanningSheetPageComponent } from './order-planning-sheet-page.component';

describe('OrderPlanningSheetPageComponent', () => {
  let component: OrderPlanningSheetPageComponent;
  let fixture: ComponentFixture<OrderPlanningSheetPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderPlanningSheetPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderPlanningSheetPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
