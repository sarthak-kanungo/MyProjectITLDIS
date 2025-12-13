import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchAssignUserToBranchComponent } from './search-assign-user-to-branch.component';

describe('SearchAssignUserToBranchComponent', () => {
  let component: SearchAssignUserToBranchComponent;
  let fixture: ComponentFixture<SearchAssignUserToBranchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchAssignUserToBranchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchAssignUserToBranchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
