import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageOrganizationHierarchySearchComponent } from './manage-organization-hierarchy-search.component';

describe('ManageOrganizationHierarchySearchComponent', () => {
  let component: ManageOrganizationHierarchySearchComponent;
  let fixture: ComponentFixture<ManageOrganizationHierarchySearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageOrganizationHierarchySearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageOrganizationHierarchySearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
