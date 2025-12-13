import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartIssueSearchComponent } from './part-issue-search.component';

describe('PartIssueSearchComponent', () => {
  let component: PartIssueSearchComponent;
  let fixture: ComponentFixture<PartIssueSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartIssueSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartIssueSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
