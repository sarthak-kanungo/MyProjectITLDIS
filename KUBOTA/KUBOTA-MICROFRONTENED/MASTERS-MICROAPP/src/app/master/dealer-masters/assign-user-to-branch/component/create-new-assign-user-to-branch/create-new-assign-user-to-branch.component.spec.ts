import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewAssignUserToBranchComponent } from './create-new-assign-user-to-branch.component';

describe('CreateNewAssignUserToBranchComponent', () => {
  let component: CreateNewAssignUserToBranchComponent;
  let fixture: ComponentFixture<CreateNewAssignUserToBranchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewAssignUserToBranchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewAssignUserToBranchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
