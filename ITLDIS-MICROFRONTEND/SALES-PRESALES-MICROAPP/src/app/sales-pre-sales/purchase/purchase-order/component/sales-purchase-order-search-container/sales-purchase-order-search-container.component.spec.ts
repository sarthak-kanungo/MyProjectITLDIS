import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesPurchaseOrderSearchContainerComponent } from './sales-purchase-order-search-container.component';

describe('SalesPurchaseOrderSearchContainerComponent', () => {
  let component: SalesPurchaseOrderSearchContainerComponent;
  let fixture: ComponentFixture<SalesPurchaseOrderSearchContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalesPurchaseOrderSearchContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesPurchaseOrderSearchContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
