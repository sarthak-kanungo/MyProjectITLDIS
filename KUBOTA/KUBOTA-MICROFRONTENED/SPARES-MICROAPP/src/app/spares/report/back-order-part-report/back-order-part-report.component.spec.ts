import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BackOrderPartReportComponent } from './back-order-part-report.component';

describe('BackOrderPartReportComponent', () => {
  let component: BackOrderPartReportComponent;
  let fixture: ComponentFixture<BackOrderPartReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BackOrderPartReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BackOrderPartReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
