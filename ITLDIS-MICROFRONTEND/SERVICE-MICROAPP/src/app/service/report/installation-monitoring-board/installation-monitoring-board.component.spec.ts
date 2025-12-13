import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstallationMonitoringBoardComponent } from './installation-monitoring-board.component';

describe('InstallationMonitoringBoardComponent', () => {
  let component: InstallationMonitoringBoardComponent;
  let fixture: ComponentFixture<InstallationMonitoringBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstallationMonitoringBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstallationMonitoringBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
