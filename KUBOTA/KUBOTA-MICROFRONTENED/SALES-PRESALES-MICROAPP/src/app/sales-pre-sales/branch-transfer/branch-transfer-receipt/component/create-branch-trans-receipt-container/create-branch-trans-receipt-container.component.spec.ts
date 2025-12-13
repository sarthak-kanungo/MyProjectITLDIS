import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBranchTransReceiptContainerComponent } from './create-branch-trans-receipt-container.component';

describe('CreateBranchTransReceiptContainerComponent', () => {
  let component: CreateBranchTransReceiptContainerComponent;
  let fixture: ComponentFixture<CreateBranchTransReceiptContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBranchTransReceiptContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBranchTransReceiptContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
