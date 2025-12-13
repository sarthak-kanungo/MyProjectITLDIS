import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchSubDealerMasterSearchComponent } from './branch-sub-dealer-master-search.component';

describe('BranchSubDealerMasterSearchComponent', () => {
  let component: BranchSubDealerMasterSearchComponent;
  let fixture: ComponentFixture<BranchSubDealerMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchSubDealerMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchSubDealerMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
