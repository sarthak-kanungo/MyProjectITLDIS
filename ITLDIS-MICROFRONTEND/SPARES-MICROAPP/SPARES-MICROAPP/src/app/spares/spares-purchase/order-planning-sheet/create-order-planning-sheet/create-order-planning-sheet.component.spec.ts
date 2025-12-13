import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOrderPlanningSheetComponent } from './create-order-planning-sheet.component';

describe('CreateOrderPlanningSheetComponent', () => {
  let component: CreateOrderPlanningSheetComponent;
  let fixture: ComponentFixture<CreateOrderPlanningSheetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateOrderPlanningSheetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateOrderPlanningSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
