import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceActivityReportCreatePageComponent } from './service-activity-report-create-page.component';

describe('ServiceActivityReportCreatePageComponent', () => {
  let component: ServiceActivityReportCreatePageComponent;
  let fixture: ComponentFixture<ServiceActivityReportCreatePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceActivityReportCreatePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceActivityReportCreatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
