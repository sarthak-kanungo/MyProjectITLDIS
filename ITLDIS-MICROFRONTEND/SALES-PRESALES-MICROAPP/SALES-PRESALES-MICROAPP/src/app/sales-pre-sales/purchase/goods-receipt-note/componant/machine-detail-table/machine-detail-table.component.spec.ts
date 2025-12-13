import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineDetailTableComponent } from './machine-detail-table.component';

describe('MachineDetailTableComponent', () => {
  let component: MachineDetailTableComponent;
  let fixture: ComponentFixture<MachineDetailTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineDetailTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineDetailTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
