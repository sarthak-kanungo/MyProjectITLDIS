import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchBranchSubDealerComponent } from './search-branch-sub-dealer.component';

describe('SearchBranchSubDealerComponent', () => {
  let component: SearchBranchSubDealerComponent;
  let fixture: ComponentFixture<SearchBranchSubDealerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchBranchSubDealerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBranchSubDealerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
