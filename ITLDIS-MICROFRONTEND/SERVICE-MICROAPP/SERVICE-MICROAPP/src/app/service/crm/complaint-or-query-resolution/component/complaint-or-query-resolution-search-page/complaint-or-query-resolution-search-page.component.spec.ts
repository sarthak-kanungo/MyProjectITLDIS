import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintOrQueryResolutionSearchPageComponent } from './complaint-or-query-resolution-search-page.component';

describe('ComplaintOrQueryResolutionSearchPageComponent', () => {
  let component: ComplaintOrQueryResolutionSearchPageComponent;
  let fixture: ComponentFixture<ComplaintOrQueryResolutionSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComplaintOrQueryResolutionSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplaintOrQueryResolutionSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
