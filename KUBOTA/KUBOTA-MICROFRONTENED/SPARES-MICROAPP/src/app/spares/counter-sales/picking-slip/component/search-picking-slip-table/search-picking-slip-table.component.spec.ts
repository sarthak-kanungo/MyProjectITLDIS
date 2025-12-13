import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPickingSlipTableComponent } from './search-picking-slip-table.component';

describe('SearchPickingSlipTableComponent', () => {
  let component: SearchPickingSlipTableComponent;
  let fixture: ComponentFixture<SearchPickingSlipTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchPickingSlipTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPickingSlipTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
