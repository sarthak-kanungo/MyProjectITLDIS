import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityReportJobCardDetailsComponent } from './activity-report-job-card-details.component';

describe('ActivityReportJobCardDetailsComponent', () => {
  let component: ActivityReportJobCardDetailsComponent;
  let fixture: ComponentFixture<ActivityReportJobCardDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivityReportJobCardDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityReportJobCardDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
