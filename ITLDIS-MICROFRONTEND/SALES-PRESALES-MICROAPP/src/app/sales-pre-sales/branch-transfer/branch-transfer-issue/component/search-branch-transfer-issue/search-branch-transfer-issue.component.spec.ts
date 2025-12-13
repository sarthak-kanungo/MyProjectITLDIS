import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBranchTransferIssueComponent } from './search-branch-transfer-issue.component';

describe('SearchBranchTransferIssueComponent', () => {
  let component: SearchBranchTransferIssueComponent;
  let fixture: ComponentFixture<SearchBranchTransferIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchBranchTransferIssueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBranchTransferIssueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
