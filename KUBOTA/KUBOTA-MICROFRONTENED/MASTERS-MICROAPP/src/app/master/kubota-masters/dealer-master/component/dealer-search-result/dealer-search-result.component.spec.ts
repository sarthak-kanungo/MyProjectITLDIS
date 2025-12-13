import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerSearchResultComponent } from './dealer-search-result.component';

describe('DealerSearchResultComponent', () => {
  let component: DealerSearchResultComponent;
  let fixture: ComponentFixture<DealerSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
