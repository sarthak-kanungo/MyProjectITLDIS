import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchSubDealerMasterCreateComponent } from './branch-sub-dealer-master-create.component';

describe('BranchSubDealerMasterCreateComponent', () => {
  let component: BranchSubDealerMasterCreateComponent;
  let fixture: ComponentFixture<BranchSubDealerMasterCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchSubDealerMasterCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchSubDealerMasterCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
