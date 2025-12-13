import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBranchTransferIndentComponent } from './search-branch-transfer-indent.component';

describe('SearchBranchTransferIndentComponent', () => {
  let component: SearchBranchTransferIndentComponent;
  let fixture: ComponentFixture<SearchBranchTransferIndentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchBranchTransferIndentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBranchTransferIndentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
