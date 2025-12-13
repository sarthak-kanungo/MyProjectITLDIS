import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignUserToBranchSearchResultComponent } from './assign-user-to-branch-search-result.component';

describe('AssignUserToBranchSearchResultComponent', () => {
  let component: AssignUserToBranchSearchResultComponent;
  let fixture: ComponentFixture<AssignUserToBranchSearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignUserToBranchSearchResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignUserToBranchSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
