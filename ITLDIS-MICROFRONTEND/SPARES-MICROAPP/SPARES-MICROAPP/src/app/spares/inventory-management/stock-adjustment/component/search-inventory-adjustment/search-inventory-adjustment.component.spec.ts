import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchInventoryAdjustmentComponent } from './search-inventory-adjustment.component';

describe('SearchInventoryAdjustmentComponent', () => {
  let component: SearchInventoryAdjustmentComponent;
  let fixture: ComponentFixture<SearchInventoryAdjustmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchInventoryAdjustmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchInventoryAdjustmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
