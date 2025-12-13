import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferIssuePageComponent } from './branch-transfer-issue-page.component';

describe('BranchTransferIssuePageComponent', () => {
  let component: BranchTransferIssuePageComponent;
  let fixture: ComponentFixture<BranchTransferIssuePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferIssuePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferIssuePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
