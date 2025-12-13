import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferIssueCreateComponent } from './branch-transfer-issue-create.component';

describe('BranchTransferIssueCreateComponent', () => {
  let component: BranchTransferIssueCreateComponent;
  let fixture: ComponentFixture<BranchTransferIssueCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferIssueCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferIssueCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
