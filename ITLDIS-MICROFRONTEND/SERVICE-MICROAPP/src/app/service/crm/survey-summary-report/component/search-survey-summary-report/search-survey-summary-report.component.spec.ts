import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchSurveySummaryReportComponent } from './search-survey-summary-report.component';

describe('SearchSurveySummaryReportComponent', () => {
  let component: SearchSurveySummaryReportComponent;
  let fixture: ComponentFixture<SearchSurveySummaryReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchSurveySummaryReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchSurveySummaryReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
