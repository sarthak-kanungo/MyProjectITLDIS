import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransReqItemDetailsContainerComponent } from './branch-trans-req-item-details-container.component';

describe('BranchTransReqItemDetailsContainerComponent', () => {
  let component: BranchTransReqItemDetailsContainerComponent;
  let fixture: ComponentFixture<BranchTransReqItemDetailsContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransReqItemDetailsContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransReqItemDetailsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
