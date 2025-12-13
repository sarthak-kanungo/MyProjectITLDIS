import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockAdjustItemDetailsComponent } from './stock-adjust-item-details.component';

describe('StockAdjustItemDetailsComponent', () => {
  let component: StockAdjustItemDetailsComponent;
  let fixture: ComponentFixture<StockAdjustItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockAdjustItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockAdjustItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
