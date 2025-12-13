import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchResultChannelFinanceIndentComponent } from './search-result-channel-finance-indent.component';

describe('SearchResultChannelFinanceIndentComponent', () => {
  let component: SearchResultChannelFinanceIndentComponent;
  let fixture: ComponentFixture<SearchResultChannelFinanceIndentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchResultChannelFinanceIndentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultChannelFinanceIndentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
