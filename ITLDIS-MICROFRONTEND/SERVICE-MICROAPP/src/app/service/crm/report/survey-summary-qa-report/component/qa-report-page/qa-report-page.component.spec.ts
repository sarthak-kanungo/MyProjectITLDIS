import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QaReportPageComponent } from './qa-report-page.component';

describe('QaReportPageComponent', () => {
  let component: QaReportPageComponent;
  let fixture: ComponentFixture<QaReportPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QaReportPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QaReportPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
