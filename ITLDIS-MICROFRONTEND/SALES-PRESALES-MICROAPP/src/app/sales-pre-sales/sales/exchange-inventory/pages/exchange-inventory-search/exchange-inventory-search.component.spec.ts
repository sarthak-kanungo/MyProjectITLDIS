import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExchangeInventorySearchComponent } from './exchange-inventory-search.component';

describe('ExchangeInventorySearchComponent', () => {
  let component: ExchangeInventorySearchComponent;
  let fixture: ComponentFixture<ExchangeInventorySearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExchangeInventorySearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExchangeInventorySearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
