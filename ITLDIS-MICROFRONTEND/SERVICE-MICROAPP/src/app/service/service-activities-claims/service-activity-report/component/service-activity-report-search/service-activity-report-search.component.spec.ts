import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceActivityReportSearchComponent } from './service-activity-report-search.component';

describe('ServiceActivityReportSearchComponent', () => {
  let component: ServiceActivityReportSearchComponent;
  let fixture: ComponentFixture<ServiceActivityReportSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceActivityReportSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceActivityReportSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
