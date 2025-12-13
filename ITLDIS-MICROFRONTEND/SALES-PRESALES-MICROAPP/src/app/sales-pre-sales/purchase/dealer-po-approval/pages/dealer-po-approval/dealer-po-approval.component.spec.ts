import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DealerPoApprovalComponent } from './dealer-po-approval.component';

describe('DealerPoApprovalComponent', () => {
  let component: DealerPoApprovalComponent;
  let fixture: ComponentFixture<DealerPoApprovalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DealerPoApprovalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DealerPoApprovalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
