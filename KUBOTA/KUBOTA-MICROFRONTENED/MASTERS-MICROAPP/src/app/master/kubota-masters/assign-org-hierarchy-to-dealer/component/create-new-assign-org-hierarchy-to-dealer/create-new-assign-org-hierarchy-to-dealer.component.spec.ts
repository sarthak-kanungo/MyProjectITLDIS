import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CreateNewAssignOrgHierarchyToDealerComponent } from './create-new-assign-org-hierarchy-to-dealer.component';


describe('CreateNewAssignOrgHierarchyToDealerComponent', () => {
  let component: CreateNewAssignOrgHierarchyToDealerComponent;
  let fixture: ComponentFixture<CreateNewAssignOrgHierarchyToDealerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewAssignOrgHierarchyToDealerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewAssignOrgHierarchyToDealerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
