import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportingEmployeeDetailsComponent } from './reporting-employee-details.component';

describe('ReportingEmployeeDetailsComponent', () => {
  let component: ReportingEmployeeDetailsComponent;
  let fixture: ComponentFixture<ReportingEmployeeDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportingEmployeeDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportingEmployeeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
