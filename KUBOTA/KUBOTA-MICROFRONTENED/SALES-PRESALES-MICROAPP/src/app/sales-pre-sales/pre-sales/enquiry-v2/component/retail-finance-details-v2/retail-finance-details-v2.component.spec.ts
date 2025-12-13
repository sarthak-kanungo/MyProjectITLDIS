import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailFinanceDetailsV2Component } from './retail-finance-details-v2.component';

describe('RetailFinanceDetailsV2Component', () => {
  let component: RetailFinanceDetailsV2Component;
  let fixture: ComponentFixture<RetailFinanceDetailsV2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetailFinanceDetailsV2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailFinanceDetailsV2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
