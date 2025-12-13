import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlatRateScheduleCodeMasterSearchComponent } from './flat-rate-schedule-code-master-search.component';

describe('FlatRateScheduleCodeMasterSearchComponent', () => {
  let component: FlatRateScheduleCodeMasterSearchComponent;
  let fixture: ComponentFixture<FlatRateScheduleCodeMasterSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlatRateScheduleCodeMasterSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatRateScheduleCodeMasterSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
