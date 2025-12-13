import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferIssueItemDetailsComponent } from './branch-transfer-issue-item-details.component';

describe('BranchTransferIssueItemDetailsComponent', () => {
  let component: BranchTransferIssueItemDetailsComponent;
  let fixture: ComponentFixture<BranchTransferIssueItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferIssueItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferIssueItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
