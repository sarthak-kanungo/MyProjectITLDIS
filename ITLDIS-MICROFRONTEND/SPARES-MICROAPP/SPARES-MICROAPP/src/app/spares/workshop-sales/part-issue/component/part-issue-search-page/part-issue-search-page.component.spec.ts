import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartIssueSearchPageComponent } from './part-issue-search-page.component';

describe('PartIssueSearchPageComponent', () => {
  let component: PartIssueSearchPageComponent;
  let fixture: ComponentFixture<PartIssueSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartIssueSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartIssueSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
