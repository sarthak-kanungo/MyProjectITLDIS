import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchAddressComponent } from './branch-address.component';

describe('BranchAddressComponent', () => {
  let component: BranchAddressComponent;
  let fixture: ComponentFixture<BranchAddressComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchAddressComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
