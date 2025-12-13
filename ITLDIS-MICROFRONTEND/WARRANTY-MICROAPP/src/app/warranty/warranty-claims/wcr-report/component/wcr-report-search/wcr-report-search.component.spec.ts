import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WcrReportSearchComponent } from './wcr-report-search.component';

describe('WcrReportSearchComponent', () => {
  let component: WcrReportSearchComponent;
  let fixture: ComponentFixture<WcrReportSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WcrReportSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WcrReportSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
