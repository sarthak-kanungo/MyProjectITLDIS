import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LastPurchaseDetailsComponent } from './last-purchase-details.component';

describe('LastPurchaseDetailsComponent', () => {
  let component: LastPurchaseDetailsComponent;
  let fixture: ComponentFixture<LastPurchaseDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LastPurchaseDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LastPurchaseDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
