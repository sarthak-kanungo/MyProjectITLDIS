import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignOrgHierarchyToDealerSearchComponent } from './assign-org-hierarchy-to-dealer-search.component';

describe('AssignOrgHierarchyToDealerSearchComponent', () => {
  let component: AssignOrgHierarchyToDealerSearchComponent;
  let fixture: ComponentFixture<AssignOrgHierarchyToDealerSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignOrgHierarchyToDealerSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignOrgHierarchyToDealerSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
