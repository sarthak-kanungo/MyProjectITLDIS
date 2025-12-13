import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchViewTrasitDetailSearchComponent } from './search-view-trasit-detail-search.component';

describe('SearchViewTrasitDetailSearchComponent', () => {
  let component: SearchViewTrasitDetailSearchComponent;
  let fixture: ComponentFixture<SearchViewTrasitDetailSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchViewTrasitDetailSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchViewTrasitDetailSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
