import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchResultsQuotationListComponent } from './search-results-quotation-list.component';

describe('SearchResultsQuotationListComponent', () => {
  let component: SearchResultsQuotationListComponent;
  let fixture: ComponentFixture<SearchResultsQuotationListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchResultsQuotationListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultsQuotationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
