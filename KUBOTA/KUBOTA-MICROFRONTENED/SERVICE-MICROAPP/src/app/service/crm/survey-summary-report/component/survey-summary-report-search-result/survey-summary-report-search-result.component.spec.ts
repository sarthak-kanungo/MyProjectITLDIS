import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveySummaryReportSearchResultComponent } from './survey-summary-report-search-result.component';

describe('SurveySummaryReportSearchResultComponent', () => {
  let component: SurveySummaryReportSearchResultComponent;
  let fixture: ComponentFixture<SurveySummaryReportSearchResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveySummaryReportSearchResultComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SurveySummaryReportSearchResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
