import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBranchTransferIssueComponent } from './create-branch-transfer-issue.component';

describe('CreateBranchTransferIssueComponent', () => {
  let component: CreateBranchTransferIssueComponent;
  let fixture: ComponentFixture<CreateBranchTransferIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBranchTransferIssueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBranchTransferIssueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
