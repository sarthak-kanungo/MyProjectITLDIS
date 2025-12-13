import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBranchIndentComponent } from './create-branch-indent.component';

describe('CreateBranchIndentComponent', () => {
  let component: CreateBranchIndentComponent;
  let fixture: ComponentFixture<CreateBranchIndentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBranchIndentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBranchIndentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
