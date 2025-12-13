import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPriceMasterSearchComponent } from './item-price-master-search.component';

describe('ItemPriceMasterSearchComponent', () => {
  let component: ItemPriceMasterSearchComponent;
  let fixture: ComponentFixture<ItemPriceMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemPriceMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemPriceMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
