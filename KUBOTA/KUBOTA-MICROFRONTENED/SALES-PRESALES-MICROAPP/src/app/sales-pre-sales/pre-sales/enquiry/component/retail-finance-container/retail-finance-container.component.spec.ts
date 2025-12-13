import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailFinanceContainerComponent } from './retail-finance-container.component';

describe('RetailFinanceContainerComponent', () => {
  let component: RetailFinanceContainerComponent;
  let fixture: ComponentFixture<RetailFinanceContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetailFinanceContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailFinanceContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
