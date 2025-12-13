import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchTransferRecieptSearchPageComponent } from './branch-transfer-reciept-search-page.component';

describe('BranchTransferRecieptSearchPageComponent', () => {
  let component: BranchTransferRecieptSearchPageComponent;
  let fixture: ComponentFixture<BranchTransferRecieptSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchTransferRecieptSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchTransferRecieptSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
