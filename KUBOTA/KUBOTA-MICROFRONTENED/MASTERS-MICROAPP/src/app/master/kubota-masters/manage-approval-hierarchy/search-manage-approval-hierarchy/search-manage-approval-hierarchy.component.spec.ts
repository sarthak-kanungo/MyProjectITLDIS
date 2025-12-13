import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchManageApprovalHierarchyComponent } from './search-manage-approval-hierarchy.component';

describe('SearchManageApprovalHierarchyComponent', () => {
  let component: SearchManageApprovalHierarchyComponent;
  let fixture: ComponentFixture<SearchManageApprovalHierarchyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchManageApprovalHierarchyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchManageApprovalHierarchyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
