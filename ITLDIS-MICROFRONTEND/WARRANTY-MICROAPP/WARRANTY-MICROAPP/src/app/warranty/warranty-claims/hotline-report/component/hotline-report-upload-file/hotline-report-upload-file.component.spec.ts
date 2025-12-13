import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotlineReportUploadFileComponent } from './hotline-report-upload-file.component';

describe('HotlineReportUploadFileComponent', () => {
  let component: HotlineReportUploadFileComponent;
  let fixture: ComponentFixture<HotlineReportUploadFileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotlineReportUploadFileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotlineReportUploadFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
