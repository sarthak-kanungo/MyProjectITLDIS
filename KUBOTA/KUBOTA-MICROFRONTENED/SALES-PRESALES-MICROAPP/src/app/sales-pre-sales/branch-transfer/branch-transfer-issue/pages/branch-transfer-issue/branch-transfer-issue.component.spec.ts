import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferIssueComponent } from './branch-transfer-issue.component';

describe('BranchTransferIssueComponent', () => {
  let component: BranchTransferIssueComponent;
  let fixture: ComponentFixture<BranchTransferIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferIssueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferIssueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
