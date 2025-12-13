import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlatRateScheduleHourlyRateMasterSearchComponent } from './flat-rate-schedule-hourly-rate-master-search.component';

describe('FlatRateScheduleHourlyRateMasterSearchComponent', () => {
  let component: FlatRateScheduleHourlyRateMasterSearchComponent;
  let fixture: ComponentFixture<FlatRateScheduleHourlyRateMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlatRateScheduleHourlyRateMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatRateScheduleHourlyRateMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
