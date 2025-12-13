import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceActivityReportSearchPageComponent } from './service-activity-report-search-page.component';

describe('ServiceActivityReportSearchPageComponent', () => {
  let component: ServiceActivityReportSearchPageComponent;
  let fixture: ComponentFixture<ServiceActivityReportSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceActivityReportSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceActivityReportSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
