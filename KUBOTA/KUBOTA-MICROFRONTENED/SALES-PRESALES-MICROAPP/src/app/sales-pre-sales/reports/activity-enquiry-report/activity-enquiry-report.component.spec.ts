import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityEnquiryReportComponent } from './activity-enquiry-report.component';

describe('ActivityEnquiryReportComponent', () => {
  let component: ActivityEnquiryReportComponent;
  let fixture: ComponentFixture<ActivityEnquiryReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityEnquiryReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityEnquiryReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
