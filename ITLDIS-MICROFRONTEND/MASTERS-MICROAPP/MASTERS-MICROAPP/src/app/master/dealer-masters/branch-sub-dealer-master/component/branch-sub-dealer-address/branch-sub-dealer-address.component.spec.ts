import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchSubDealerAddressComponent } from './branch-sub-dealer-address.component';

describe('BranchSubDealerAddressComponent', () => {
  let component: BranchSubDealerAddressComponent;
  let fixture: ComponentFixture<BranchSubDealerAddressComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchSubDealerAddressComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchSubDealerAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
