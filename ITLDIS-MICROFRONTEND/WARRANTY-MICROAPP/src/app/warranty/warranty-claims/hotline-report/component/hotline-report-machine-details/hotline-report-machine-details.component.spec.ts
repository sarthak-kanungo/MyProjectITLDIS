import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotlineReportMachineDetailsComponent } from './hotline-report-machine-details.component';

describe('HotlineReportMachineDetailsComponent', () => {
  let component: HotlineReportMachineDetailsComponent;
  let fixture: ComponentFixture<HotlineReportMachineDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotlineReportMachineDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotlineReportMachineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
