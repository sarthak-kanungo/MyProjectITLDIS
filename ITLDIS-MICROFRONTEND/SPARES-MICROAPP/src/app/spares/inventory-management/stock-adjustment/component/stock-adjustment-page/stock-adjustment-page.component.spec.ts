import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockAdjustmentPageComponent } from './stock-adjustment-page.component';

describe('StockAdjustmentPageComponent', () => {
  let component: StockAdjustmentPageComponent;
  let fixture: ComponentFixture<StockAdjustmentPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockAdjustmentPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockAdjustmentPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
