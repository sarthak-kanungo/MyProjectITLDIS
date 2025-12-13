import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotlineReportVendorResponseComponent } from './hotline-report-vendor-response.component';

describe('HotlineReportVendorResponseComponent', () => {
  let component: HotlineReportVendorResponseComponent;
  let fixture: ComponentFixture<HotlineReportVendorResponseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotlineReportVendorResponseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotlineReportVendorResponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
