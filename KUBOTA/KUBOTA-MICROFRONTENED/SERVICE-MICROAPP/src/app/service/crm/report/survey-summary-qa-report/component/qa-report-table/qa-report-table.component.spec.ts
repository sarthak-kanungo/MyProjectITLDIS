import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QaReportTableComponent } from './qa-report-table.component';

describe('QaReportTableComponent', () => {
  let component: QaReportTableComponent;
  let fixture: ComponentFixture<QaReportTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QaReportTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QaReportTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
