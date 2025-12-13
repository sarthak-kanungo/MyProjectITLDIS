import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBranchTransIssueContainerComponent } from './create-branch-trans-issue-container.component';

describe('CreateBranchTransIssueContainerComponent', () => {
  let component: CreateBranchTransIssueContainerComponent;
  let fixture: ComponentFixture<CreateBranchTransIssueContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBranchTransIssueContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBranchTransIssueContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
