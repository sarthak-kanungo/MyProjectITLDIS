import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateManageApprovalHierarchyComponent } from './create-manage-approval-hierarchy.component';

describe('CreateManageApprovalHierarchyComponent', () => {
  let component: CreateManageApprovalHierarchyComponent;
  let fixture: ComponentFixture<CreateManageApprovalHierarchyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateManageApprovalHierarchyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateManageApprovalHierarchyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
