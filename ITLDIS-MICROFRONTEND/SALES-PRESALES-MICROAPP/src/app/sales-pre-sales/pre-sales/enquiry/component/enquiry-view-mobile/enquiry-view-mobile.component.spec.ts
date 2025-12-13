import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnquiryViewMobileComponent } from './enquiry-view-mobile.component';

describe('EnquiryViewMobileComponent', () => {
  let component: EnquiryViewMobileComponent;
  let fixture: ComponentFixture<EnquiryViewMobileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnquiryViewMobileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnquiryViewMobileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
