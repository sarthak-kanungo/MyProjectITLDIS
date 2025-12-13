import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchComplaintOrQueryResolutionComponent } from './search-complaint-or-query-resolution.component';

describe('SearchComplaintOrQueryResolutionComponent', () => {
  let component: SearchComplaintOrQueryResolutionComponent;
  let fixture: ComponentFixture<SearchComplaintOrQueryResolutionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchComplaintOrQueryResolutionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComplaintOrQueryResolutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
