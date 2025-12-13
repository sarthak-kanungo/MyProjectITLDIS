import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WcrReportComponent } from './wcr-report.component';

describe('WcrReportComponent', () => {
  let component: WcrReportComponent;
  let fixture: ComponentFixture<WcrReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WcrReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WcrReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
