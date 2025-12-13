import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceActivityReportDetailsComponent } from './service-activity-report-details.component';

describe('ServiceActivityReportDetailsComponent', () => {
  let component: ServiceActivityReportDetailsComponent;
  let fixture: ComponentFixture<ServiceActivityReportDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceActivityReportDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceActivityReportDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
