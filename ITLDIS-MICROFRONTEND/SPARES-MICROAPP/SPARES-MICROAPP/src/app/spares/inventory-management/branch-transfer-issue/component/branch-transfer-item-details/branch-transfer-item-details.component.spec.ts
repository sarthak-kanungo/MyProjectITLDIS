import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferItemDetailsComponent } from './branch-transfer-item-details.component';

describe('BranchTransferItemDetailsComponent', () => {
  let component: BranchTransferItemDetailsComponent;
  let fixture: ComponentFixture<BranchTransferItemDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferItemDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
