import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDelearCustomerCareExCallComponent } from './search-delear-customer-care-ex-call.component';

describe('SearchDelearCustomerCareExCallComponent', () => {
  let component: SearchDelearCustomerCareExCallComponent;
  let fixture: ComponentFixture<SearchDelearCustomerCareExCallComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchDelearCustomerCareExCallComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchDelearCustomerCareExCallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
