import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchViewTrasitDetailPageComponent } from './search-view-trasit-detail-page.component';

describe('SearchViewTrasitDetailPageComponent', () => {
  let component: SearchViewTrasitDetailPageComponent;
  let fixture: ComponentFixture<SearchViewTrasitDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchViewTrasitDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchViewTrasitDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
