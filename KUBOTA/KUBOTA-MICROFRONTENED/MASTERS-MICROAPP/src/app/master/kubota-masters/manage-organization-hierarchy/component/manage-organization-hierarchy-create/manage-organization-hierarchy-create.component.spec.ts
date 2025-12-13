import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageOrganizationHierarchyCreateComponent } from './manage-organization-hierarchy-create.component';

describe('ManageOrganizationHierarchyCreateComponent', () => {
  let component: ManageOrganizationHierarchyCreateComponent;
  let fixture: ComponentFixture<ManageOrganizationHierarchyCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageOrganizationHierarchyCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageOrganizationHierarchyCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
