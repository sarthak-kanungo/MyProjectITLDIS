import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotlineReportSearchComponent } from './hotline-report-search.component';

describe('HotlineReportSearchComponent', () => {
  let component: HotlineReportSearchComponent;
  let fixture: ComponentFixture<HotlineReportSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotlineReportSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotlineReportSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
