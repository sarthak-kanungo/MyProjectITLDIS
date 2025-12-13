import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerMasterChangeRequestComponent } from './customer-master-change-request.component';

describe('CustomerMasterChangeRequestComponent', () => {
  let component: CustomerMasterChangeRequestComponent;
  let fixture: ComponentFixture<CustomerMasterChangeRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerMasterChangeRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerMasterChangeRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
