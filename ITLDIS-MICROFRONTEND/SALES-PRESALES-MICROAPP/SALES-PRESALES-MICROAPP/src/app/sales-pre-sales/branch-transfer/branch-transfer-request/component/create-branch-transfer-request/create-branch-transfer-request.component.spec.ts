import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBranchTransferRequestComponent } from './create-branch-transfer-request.component';

describe('CreateBranchTransferRequestComponent', () => {
  let component: CreateBranchTransferRequestComponent;
  let fixture: ComponentFixture<CreateBranchTransferRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBranchTransferRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBranchTransferRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
