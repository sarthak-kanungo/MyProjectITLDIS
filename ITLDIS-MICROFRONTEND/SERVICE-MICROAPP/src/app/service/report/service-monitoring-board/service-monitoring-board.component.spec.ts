import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceMonitoringBoardComponent } from './service-monitoring-board.component';

describe('ServiceMonitoringBoardComponent', () => {
  let component: ServiceMonitoringBoardComponent;
  let fixture: ComponentFixture<ServiceMonitoringBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceMonitoringBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceMonitoringBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
