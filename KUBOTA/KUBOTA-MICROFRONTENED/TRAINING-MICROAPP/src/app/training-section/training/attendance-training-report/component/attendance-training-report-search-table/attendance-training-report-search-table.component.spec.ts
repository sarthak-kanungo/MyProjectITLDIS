import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceTrainingReportSearchTableComponent } from './attendance-training-report-search-table.component';

describe('AttendanceTrainingReportSearchTableComponent', () => {
  let component: AttendanceTrainingReportSearchTableComponent;
  let fixture: ComponentFixture<AttendanceTrainingReportSearchTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttendanceTrainingReportSearchTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendanceTrainingReportSearchTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
