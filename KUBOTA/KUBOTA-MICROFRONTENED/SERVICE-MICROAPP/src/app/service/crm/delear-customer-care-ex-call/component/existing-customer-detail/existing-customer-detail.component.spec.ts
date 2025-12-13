import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExistingCustomerDetailComponent } from './existing-customer-detail.component';

describe('ExistingCustomerDetailComponent', () => {
  let component: ExistingCustomerDetailComponent;
  let fixture: ComponentFixture<ExistingCustomerDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExistingCustomerDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExistingCustomerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
