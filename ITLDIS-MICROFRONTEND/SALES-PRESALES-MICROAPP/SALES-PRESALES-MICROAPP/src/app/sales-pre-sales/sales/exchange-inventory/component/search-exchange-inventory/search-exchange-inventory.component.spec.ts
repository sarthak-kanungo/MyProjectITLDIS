import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchExchangeInventoryComponent } from './search-exchange-inventory.component';

describe('SearchExchangeInventoryComponent', () => {
  let component: SearchExchangeInventoryComponent;
  let fixture: ComponentFixture<SearchExchangeInventoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchExchangeInventoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchExchangeInventoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
