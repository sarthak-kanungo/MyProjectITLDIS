import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotlineReportSearchPageComponent } from './hotline-report-search-page.component';

describe('HotlineReportSearchPageComponent', () => {
  let component: HotlineReportSearchPageComponent;
  let fixture: ComponentFixture<HotlineReportSearchPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotlineReportSearchPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotlineReportSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
