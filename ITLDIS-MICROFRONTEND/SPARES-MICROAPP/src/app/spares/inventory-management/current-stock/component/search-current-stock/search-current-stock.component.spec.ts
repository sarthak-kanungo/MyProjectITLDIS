import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCurrentStockComponent } from './search-current-stock.component';

describe('SearchCurrentStockComponent', () => {
  let component: SearchCurrentStockComponent;
  let fixture: ComponentFixture<SearchCurrentStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchCurrentStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchCurrentStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
